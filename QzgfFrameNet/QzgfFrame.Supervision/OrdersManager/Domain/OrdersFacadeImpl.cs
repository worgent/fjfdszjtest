using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Supervision.OrdersManager.Models;
using Newtonsoft.Json;
using QzgfFrame.Supervision.SendManager.Models;

namespace QzgfFrame.Supervision.OrdersManager.Domain
{
    /// <summary>
    /// 下单表:下单/接单
    /// </summary>
    public class OrdersFacadeImpl : OrdersFacade
    {
        private IRepository<SupervisionOrders> ordersRepository { set; get; }
        private IRepository<SupervisionSend> sendRepository { set; get; }

        public SupervisionOrders Get(object id)
        {
            return ordersRepository.Get(id.ToString());
        }

        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = ordersRepository.Delete(s);
            }
            return result;
        }

        public bool Save(SupervisionOrders entity)
        {
            entity.Id = ordersRepository.NewSequence("SYSTEM_MENU");
            return ordersRepository.Save(entity);
        }

        public bool Update(SupervisionOrders entity)
        {
            return ordersRepository.Update(entity);
        }

        public IList<SupervisionOrders> LoadAll()
        {
            return ordersRepository.LoadAll();
        }

        public IList<SupervisionOrders> LoadAll(string order, string where)
        {
            return ordersRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from SupervisionOrders";
            IList<SupervisionOrders> ls = ordersRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = ordersRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        public string FindByPage(int pageNo, int pageSize, string[] gridsearch)
        {
            string sql = "from SupervisionOrders";
            string[] gridsearchs = null;
            string url = "";
            string urlTile = "";
            string approvalUrl = "";
            string approvalurlTile = "";
            if (gridsearch != null)
            {
                for (int i=0;i< gridsearch.Count();i++)
                {
                    if (i == 0)
                    {
                        //查询条件，如果是“或”条件，以“/”号为分割
                        if (gridsearch[i].Split('/').Count() > 1)
                        {
                            gridsearchs = gridsearch[i].Split('/');
                            for (int j = 0; j < gridsearchs.Count(); j++)
                            {
                                if (j == 0)
                                {
                                    sql += " where " + gridsearchs[j];
                                }
                                else
                                {
                                    sql += " or " + gridsearchs[j];
                                }
                            }
                        }
                        else
                        {
                            sql += " where " + gridsearch[i];
                        }
                    }
                    else
                    {
                        if (gridsearch[i].Split('/').Count() > 1)
                        {
                            gridsearchs = gridsearch[i].Split('/');
                            for (int j = 0; j < gridsearchs.Count(); j++)
                            {
                                sql += " or " + gridsearchs[j];
                            }
                        }
                        else
                        {
                            sql += " and " + gridsearch[i];
                        }
                    }

                    //定义列表中的操作列
                    if (gridsearch[i] == "ApprovalStatus='0'")
                    {
                        approvalUrl = "<a href='javascript:f_Approval(";
                        approvalurlTile = ");'>审核工单</a>";
                    }
                    else if (gridsearch[i] == "ApprovalStatus='1'")
                    {
                        approvalUrl = "<a href='javascript:f_Detailed(";
                        approvalurlTile = ");'>查看</a>";
                    }
                    else
                    {
                        //根据查询条件，调用相应的函数
                        switch (gridsearch[i])
                        {
                            case "Status='0'":
                                url = "<a href='javascript:f_Order(";
                                urlTile = ");'>接单</a>";
                                break;
                            case "Status='1'":
                                url = "<a href='javascript:f_Back(";
                                urlTile = ");'>回单</a>";
                                break;
                            case "Status='2'":
                                url = "<a href='javascript:f_Detailed(";
                                urlTile = ");'>查看</a>";
                                break;
                            case "Status='3'":
                                url = "<a href='javascript:f_Detailed(";
                                urlTile = ");'>查看</a>";
                                break;
                            case "Status='4'":
                                url = "<a href='javascript:f_ReBack(";
                                urlTile = ");'>重新回单</a>";
                                break;
                        }
                    }
                }
            }

            //未判断是该单位该人员的工单.必须添加条件：1、单位编号；2、人员编号
            IList<SupervisionOrders> ls = ordersRepository.FindByPage(pageNo, pageSize, sql);
            IList<Order> orders = new List<Order>();
            for (int i = 0; i < ls.Count; i++)
            {
                Order order = new Order();
                SupervisionSend supervisionSend = sendRepository.Get(ls[i].SendId);
                order.Operating = url + '"'+ls[i].Id + ',' + supervisionSend.Id + '"' + urlTile;
                order.ApprovalOperating = approvalUrl + '"' + ls[i].Id + ',' + supervisionSend.Id + '"' + approvalurlTile;
                order.Id = ls[i].Id;
                order.SendTitle = supervisionSend.SendTitle;
                order.SendContent = supervisionSend.SendContent;
                order.DispatchTime = supervisionSend.DispatchTime.ToString();
                order.SendUnitName = ls[i].SendUnitName;
                if(ls[i].Status != null)
                {
                    if (ls[i].Status == "3")
                    {
                        order.Status = "通过";
                    }
                    else if (ls[i].Status == "4")
                    {
                        order.Status = "退回";
                    }
                }
                orders.Add(order);
            }

            string rowsjson = JsonConvert.SerializeObject(orders);
            int recordCount = ordersRepository.FindByPageCount(sql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
