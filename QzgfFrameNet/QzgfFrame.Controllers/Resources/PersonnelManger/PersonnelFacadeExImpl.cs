using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Collections;
using System.Text;
using QzgfFrame.Resources.PersonnelManger.Domain;
using QzgfFrame.Resources.PersonnelManger.Models;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CertificateManger.Models;
using QzgfFrame.Archives.CertificateManger.Domain;
using QzgfFrame.Archives.StagnationManger.Models;
using QzgfFrame.Archives.StagnationManger.Domain;
using QzgfFrame.Archives.DiplomaManger.Models;
using QzgfFrame.Archives.DiplomaManger.Domain;
using QzgfFrame.Archives.DutyManger.Models;
using QzgfFrame.Archives.DutyManger.Domain;
using QzgfFrame.Archives.QualificationTypeManger.Models;
using QzgfFrame.Archives.QualificationTypeManger.Domain;
using QzgfFrame.Archives.MaintainSpecialtyManger.Models;
using QzgfFrame.Archives.MaintainSpecialtyManger.Domain;
using QzgfFrame.Archives.ItemPropertyManger.Models;
using QzgfFrame.Archives.ItemPropertyManger.Domain;
using QzgfFrame.Archives.PostsManger.Models;
using QzgfFrame.Archives.PostsManger.Domain;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;
using Newtonsoft.Json;

namespace QzgfFrame.Controllers.Resources.PersonnelManger
{
    public class PersonnelFacadeExImpl : PersonnelFacadeEx
    {
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private PersonnelFacade personnelFacade { set; get; }
        private DutyFacade dutyFacade { set; get; }
        private MaintainSpecialtyFacade maintainSpecialtyFacade { set; get; }
        private QualificationTypeFacade qualificationTypeFacade { set; get; }
        private ItemPropertyFacade itemPropertyFacade { set; get; }
        private StagnationFacade stagnationFacade { set; get; }
        private DiplomaFacade diplomaFacade { set; get; }
        private PostsFacade postsFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private CertificateFacade certificateFacade { set; get; }

        public string[] GetDropDownList(string hql)
        {
            string[] arry = new string[2];
            IList<ArchiveStagnation> stagnations = null;
            IList<ArchiveGrid> grids = null;
            stagnations = stagnationFacade.LoadLAll(hql);
            grids = gridFacade.LoadLAll(hql);
            IList mapList = new ArrayList();
            foreach (ArchiveStagnation item in stagnations)
            {
                mapList.Add(new
                {
                    id = item.Id,
                    text = item.StagnationName,
                    did=item.DistrictId,
                    cid=item.CompanyId,
                    cityid=item.CityId
                });
            }
            arry[0] = JsonConvert.SerializeObject(mapList);
            mapList.Clear();
            foreach (ArchiveGrid item in grids)
            {
                mapList.Add(new
                {
                    id = item.Id,
                    text = item.GridName,
                    did=item.DistrictId,
                    cid=item.CompanyId,
                    cityid=item.CityId
                });
            }
            arry[1] = JsonConvert.SerializeObject(mapList);
            return arry;
        }
        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 33)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "二维码")
                {
                    procInfo += "文件中未找到'二维码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "人员姓名 *")
                {
                    procInfo += "文件中未找到'人员姓名'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "性 别 *")
                {
                    procInfo += "文件中未找到'性 别'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 4);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "出生日期 *")
                {
                    procInfo += "文件中未找到'出生日期'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "身份证号 *")
                {
                    procInfo += "文件中未找到'身份证号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "手机号码")
                {
                    procInfo += "文件中未找到'手机号码'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "学历文凭 *")
                {
                    procInfo += "文件中未找到'学历文凭'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 8);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "人员岗位 *")
                {
                    procInfo += "文件中未找到'人员岗位'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 9);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "人员职责 *")
                {
                    procInfo += "文件中未找到'人员职责'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 10);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "移动公司上岗证1")
                {
                    procInfo += "文件中未找到'移动公司上岗证1'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 11);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "上岗证1颁发时间")
                {
                    procInfo += "文件中未找到'上岗证1颁发时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 12);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "移动公司上岗证2")
                {
                    procInfo += "文件中未找到'移动公司上岗证2'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 13);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "上岗证2颁发时间")
                {
                    procInfo += "文件中未找到'上岗证2颁发时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 14);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "移动公司上岗证3")
                {
                    procInfo += "文件中未找到'移动公司上岗证3'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 15);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "上岗证3颁发时间")
                {
                    procInfo += "文件中未找到'上岗证3颁发时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 16);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "移动公司上岗证4")
                {
                    procInfo += "文件中未找到'移动公司上岗证4'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 17);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "上岗证4颁发时间")
                {
                    procInfo += "文件中未找到'上岗证4颁发时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 18);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "移动公司上岗证5")
                {
                    procInfo += "文件中未找到'移动公司上岗证5'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 19);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "上岗证5颁发时间")
                {
                    procInfo += "文件中未找到'上岗证5颁发时间'列";
                    isAllValid = false;
                }

                value = myArray.GetValue(1, 20);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "认证资历类型 *")
                {
                    procInfo += "文件中未找到'认证资历类型'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 21);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "认证编号")
                {
                    procInfo += "文件中未找到'认证编号'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 22);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属地市 *")
                {
                    procInfo += "文件中未找到'所属地市'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 23);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属区县")
                {
                    procInfo += "文件中未找到'所属区县'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 24);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属公司")
                {
                    procInfo += "文件中未找到'所属公司'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 25);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属驻点")
                {
                    procInfo += "文件中未找到'所属驻点'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 26);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "工作时间")
                {
                    procInfo += "文件中未找到'工作时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 27);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "入职时间 *")
                {
                    procInfo += "文件中未找到'入职时间'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 28);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否骨干 *")
                {
                    procInfo += "文件中未找到'是否骨干'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 29);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "项目名称")
                {
                    procInfo += "文件中未找到'项目名称'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 30);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "项目属性 *")
                {
                    procInfo += "文件中未找到'项目属性'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 31);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "维护专业 *")
                {
                    procInfo += "文件中未找到'维护专业'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 32);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "是否自维 *")
                {
                    procInfo += "文件中未找到'是否自维'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 33);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "备注")
                {
                    procInfo += "文件中未找到'备注'列";
                    isAllValid = false;
                }
                value = myArray.GetValue(1, 34);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "所属网格")
                {
                    procInfo += "文件中未找到'所属网格'列";
                    isAllValid = false;
                }
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 33)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                    procInfo += "身份证不可为空!!";
                else
                {
                    ResourcePersonnel Personnel = personnelFacade.GetHql(str);
                    if (Personnel == null)
                    {
                        value = myArray.GetValue(1, 2);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == ""&&str.Length>20)
                        {
                            procInfo += "人员姓名不可为空或长度大于20!!";
                        }
                        value = myArray.GetValue(1, 3);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (str.Trim() != "男" && str.Trim() != "女")
                                procInfo += "性别格式错误!!";
                        }
                        else
                            procInfo += "性别不可为空!!";
                        value = myArray.GetValue(1, 4);
                        DateTime? birthtime = ExtensionMethods.ToDateOANull(value);
                        if (birthtime == null)
                        {
                            procInfo += "出生日期不可为空或格式错误!!";
                        }
                        value = myArray.GetValue(1, 5);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.CardCheck(str))
                            {
                                procInfo += "身份证号格式不对,能是15位或18位!!";
                            }
                        }
                        // else
                        //  procInfo += "身份证号不可为空!!";

                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.IsNumeric(str) || str.Length > 15)
                            {
                                procInfo += "手机号码格式不对,只能是小于等于15位数字!!";
                            }
                        }
                        else
                            procInfo += "手机号码不可为空!!";
                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDiploma diploma = new ArchiveDiploma();
                        if (str.Trim() != "")
                        {
                            diploma = diplomaFacade.GetHql(str);
                            if (diploma == null)
                                procInfo += "该学历文凭在系统中不存在!!";
                        }
                        else
                            procInfo += "学历文凭不可为空!!";
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDuty duty = new ArchiveDuty();
                        if (str.Trim() != "")
                        {
                            duty = dutyFacade.GetHql(str);
                            if (duty == null)
                                procInfo += "该人员岗位在系统中不存在!!";
                        }
                        else
                            procInfo += "人员岗位不可为空!!";

                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        ArchivePosts posts = new ArchivePosts();
                        if (str.Trim() != "")
                        {
                            posts = postsFacade.GetHql(str);
                            if (posts == null)
                                procInfo += "该人员职责在系统中不存在!!";
                        }
                        else
                            procInfo += "人员职责不可为空!!";

                        value = myArray.GetValue(1, 11);
                        DateTime? dt1 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt1 = ExtensionMethods.ToDateOANull(value);
                            if (dt1 == null)
                            {
                                procInfo += "上岗证1颁发时间格式错误!!";
                            }
                        }
                        value = myArray.GetValue(1, 13);
                        DateTime? dt2 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt2 = ExtensionMethods.ToDateOANull(value);
                            if (dt2 == null)
                            {
                                procInfo += "上岗证2颁发时间格式错误!!";
                            }
                        }
                        value = myArray.GetValue(1, 15);
                        DateTime? dt3 = ExtensionMethods.ToDateOANull(value);
                        if (dt3 == null)
                        {
                            procInfo += "上岗证3颁发时间不可为空或格式错误!!";
                        }
                        value = myArray.GetValue(1, 17);
                        DateTime? dt4 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt4 = ExtensionMethods.ToDateOANull(value);
                            if (dt4 == null)
                            {
                                procInfo += "上岗证4颁发时间格式错误!!";
                            }
                        }
                        value = myArray.GetValue(1, 19);
                        DateTime? dt5 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt5 = ExtensionMethods.ToDateOANull(value);
                            if (dt5 == null)
                            {
                                procInfo += "上岗证5颁发时间格式错误!!";
                            }
                        }

                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveQualificationType qualificationType = new ArchiveQualificationType();
                        if (str.Trim() != "")
                        {
                            qualificationType = qualificationTypeFacade.GetHql(str);
                            if (qualificationType == null)
                                procInfo += "该认证资历类型在系统中不存在!!";
                        }
                        else
                            procInfo += "认证资历类型不可为空!!";
                        /**
                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            procInfo += "认证编号不可为空!!";
                        }**/
                        value = myArray.GetValue(1, 22);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict city = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            city = districtFacade.GetHql(str);
                            if (city == null)
                                procInfo += "该所属地市在系统中不存在!!";
                        }
                        else
                        {
                            procInfo += "所属地市不可为空!!";
                        }

                        value = myArray.GetValue(1, 23);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict district = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            district = districtFacade.GetHql(str);
                            if (district == null)
                                procInfo += "该所属区县在系统中不存在!!";
                        }
                        value = myArray.GetValue(1, 24);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveCompany company = new ArchiveCompany();
                        if (str.Trim() != "")
                        {
                            company = companyFacade.GetHql(str);
                            if (company == null)
                                procInfo += "该所属公司在系统中不存在!!";
                        }
                        value = myArray.GetValue(1, 25);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveStagnation stagnation = new ArchiveStagnation();
                        if (str.Trim() != "")
                        {
                            if (company != null && district!=null)
                            {
                                stagnation = stagnationFacade.GetHql(str, " and CompanyId='"+company.Id+"' and DistrictId='"+district.Id+"'");
                                if (stagnation == null)
                                    procInfo += "该所属驻点不在该所属区县，所属公司内!!";
                            }
                            else
                                procInfo += "请先填写区县跟公司,再能检查驻点!!";
                            
                        }

                        value = myArray.GetValue(1, 26);
                        DateTime? wokedate = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            wokedate = ExtensionMethods.ToDateOANull(value);
                            if (wokedate == null)
                            {
                                procInfo += "工作时间格式错误!!";
                            }
                        }
                        //工作时间可不可为空
                        value = myArray.GetValue(1, 27);
                        DateTime? EntryDate = ExtensionMethods.ToDateOANull(value);
                        if (EntryDate == null)
                        {
                            procInfo += "入职时间不可为空或格式错误!!";
                        }
                        value = myArray.GetValue(1, 28);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "是否骨干不可为空!!";
                        }
                        value = myArray.GetValue(1, 30);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveItemProperty itemProperty = new ArchiveItemProperty();
                        if (str.Trim() != "")
                        {
                            itemProperty = itemPropertyFacade.GetHql(str);
                            if (itemProperty == null)
                                procInfo += "该项目属性在系统中不存在!!";
                        }
                        else
                            procInfo += "项目属性不可为空!!";

                        value = myArray.GetValue(1, 31);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveMaintainSpecialty maintainSpecialty = new ArchiveMaintainSpecialty();
                        if (str.Trim() != "")
                        {
                            maintainSpecialty = maintainSpecialtyFacade.GetHql(str);
                            if (maintainSpecialty == null)
                                procInfo += "该维护专业在系统中不存在!!";
                        }
                        else
                            procInfo += "维护专业不可为空!!";

                        value = myArray.GetValue(1, 32);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "是否自维不可为空!!";
                        }
                        value = myArray.GetValue(1, 34);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveGrid grid = new ArchiveGrid();
                        if (str.Trim() != "")
                        {
                            if (company != null && district != null)
                            {
                                grid = gridFacade.GetHql(str, " and CompanyId='" + company.Id + "' and DistrictId='" + district.Id + "'");
                                if (grid == null)
                                    procInfo += "该所属网格不在该所属区县，所属公司内!!";
                            }
                            else
                                procInfo += "请先填写区县跟公司,再能检查网格!!";
                            
                        }
                    }
                    else
                        procInfo += "该人员信息在系统中已存在!!";
                }
            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string userid, string iFlag)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < 33)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                ResourcePersonnel entity = new ResourcePersonnel();
                string str = ""; object value = null;
                 value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str == "")
                {
                    procInfo += "身份证不可为空!!";
                    isAllValid = false;
                }
                else
                {
                    entity = personnelFacade.GetHql(str);
                    if (entity == null)
                    {
                        entity = new ResourcePersonnel();
                        value = myArray.GetValue(1, 1);
                        entity.TwoDimensionalCode = ExtensionMethods.ToStr(value);

                        value = myArray.GetValue(1, 2);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == ""&&str.Length>20)
                        {
                            procInfo += "人员姓名不可为空或长度大于20!!";
                            isAllValid = false;
                        }
                        entity.FullName = str;
                        value = myArray.GetValue(1, 3);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (str.Trim() != "男" && str.Trim() != "女")
                            {
                                procInfo += "性别格式错误!!";
                                isAllValid = false;
                            }
                            else
                            {
                                entity.Sex = ExtensionMethods.ObjSexToShort(value);
                            }
                        }
                        else
                        {
                            procInfo += "性别不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 4);
                        DateTime? birthtime = ExtensionMethods.ToDateOANull(value);
                        if (birthtime == null)
                        {
                            procInfo += "出生日期不可为空或格式错误!!";
                            isAllValid = false;
                        }
                        entity.BirthDate = birthtime;
                        value = myArray.GetValue(1, 5);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.CardCheck(str))
                            {
                                procInfo += "身份证号格式不对,只能等于15位数字或18位数字!!";
                                isAllValid = false;
                            }
                        }
                        entity.IDCardNumber = str;

                        value = myArray.GetValue(1, 6);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            if (!ExtensionMethods.IsNumeric(str) || str.Length > 15)
                            {
                                procInfo += "手机号码格式不对,只能是数字!!";
                                isAllValid = false;
                            }
                        }
                        else
                        {
                            procInfo += "手机号码不可为空!!";
                            isAllValid = false;
                        }
                        entity.MobileNumber = str;

                        value = myArray.GetValue(1, 7);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDiploma diploma = new ArchiveDiploma();
                        if (str.Trim() != "")
                        {
                            diploma = diplomaFacade.GetHql(str);
                            if (diploma == null)
                            {
                                procInfo += "该学历文凭在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.DiplomaId = diploma.Id;
                        }
                        else
                        {
                            procInfo += "学历文凭不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 8);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDuty duty = new ArchiveDuty();
                        if (str.Trim() != "")
                        {
                            duty = dutyFacade.GetHql(str);
                            if (duty == null)
                            {
                                procInfo += "该人员岗位在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.DutyId = duty.Id;
                        }
                        else
                        {
                            procInfo += "人员岗位不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 9);
                        str = ExtensionMethods.ToStr(value);
                        ArchivePosts posts = new ArchivePosts();
                        if (str.Trim() != "")
                        {
                            posts = postsFacade.GetHql(str);
                            if (posts == null)
                            {
                                procInfo += "该人员职责在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.PostsId = posts.Id;
                        }
                        else
                        {
                            procInfo += "人员职责不可为空!!";
                            isAllValid = false;
                        }
                        value = myArray.GetValue(1, 10);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveCertificate certificate = new ArchiveCertificate();
                        if (str.Trim() != "")
                        {
                            certificate = certificateFacade.GetHql(str);
                            if (certificate == null)
                            {
                                procInfo += "该移动公司上岗证1在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.Certificate1 = certificate.Id;
                        }
                        value = myArray.GetValue(1, 11);
                        DateTime? dt1 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt1 = ExtensionMethods.ToDateOANull(value);
                            if (dt1 == null)
                            {
                                procInfo += "上岗证1颁发时间格式错误!!";
                                isAllValid = false;
                            }
                        }

                        entity.CertificateAwardDate1 = dt1;
                        value = myArray.GetValue(1, 12);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            certificate = certificateFacade.GetHql(str);
                            if (certificate == null)
                            {
                                procInfo += "该移动公司上岗证2在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.Certificate2 = certificate.Id;
                        }
                        value = myArray.GetValue(1, 13);
                        DateTime? dt2 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt2 = ExtensionMethods.ToDateOANull(value);
                            if (dt2 == null)
                            {
                                procInfo += "上岗证2颁发时间格式错误!!";
                                isAllValid = false;
                            }
                        }
                        entity.CertificateAwardDate2 = dt2;
                        value = myArray.GetValue(1, 14);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            certificate = certificateFacade.GetHql(str);
                            if (certificate == null)
                            {
                                procInfo += "该移动公司上岗证3在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.Certificate3 = certificate.Id;
                        }
                        value = myArray.GetValue(1, 15);
                        DateTime? dt3 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt3 = ExtensionMethods.ToDateOANull(value);
                            if (dt3 == null)
                            {
                                procInfo += "上岗证3颁发时间格式错误!!";
                                isAllValid = false;
                            }
                        }
                        entity.CertificateAwardDate3 = dt3;
                        value = myArray.GetValue(1, 16);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            certificate = certificateFacade.GetHql(str);
                            if (certificate == null)
                            {
                                procInfo += "该移动公司上岗证4在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.Certificate4 = certificate.Id;
                        }
                        value = myArray.GetValue(1, 17);
                        DateTime? dt4 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt4 = ExtensionMethods.ToDateOANull(value);
                            if (dt4 == null)
                            {
                                procInfo += "上岗证4颁发时间格式错误!!";
                                isAllValid = false;
                            }
                        }
                        entity.CertificateAwardDate4 = dt4;
                        value = myArray.GetValue(1, 18);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            certificate = certificateFacade.GetHql(str);
                            if (certificate == null)
                            {
                                procInfo += "该移动公司上岗证5在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.Certificate5 = certificate.Id;
                        }
                        value = myArray.GetValue(1, 19);
                        DateTime? dt5 = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            dt5 = ExtensionMethods.ToDateOANull(value);
                            if (dt5 == null)
                            {
                                procInfo += "上岗证5颁发时间格式错误!!";
                                isAllValid = false;
                            }
                        }
                        entity.CertificateAwardDate5 = dt5;

                        value = myArray.GetValue(1, 20);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveQualificationType qualificationType = new ArchiveQualificationType();
                        if (str.Trim() != "")
                        {
                            qualificationType = qualificationTypeFacade.GetHql(str);
                            if (qualificationType == null)
                            {
                                procInfo += "该认证资历类型在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.QualificationTypeId = qualificationType.Id;
                        }
                        else
                        {
                            procInfo += "认证资历类型不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 21);
                        str = ExtensionMethods.ToStr(value);
                        entity.CertificationNo = str;

                        value = myArray.GetValue(1, 22);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict city = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            city = districtFacade.GetHql(str);
                            if (city == null)
                            {
                                procInfo += "该所属地市在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.CityId = city.Id;
                        }
                        else
                        {
                            procInfo += "所属地市不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 23);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveDistrict district = new ArchiveDistrict();
                        if (str.Trim() != "")
                        {
                            district = districtFacade.GetHql(str);
                            if (district == null)
                            {
                                procInfo += "该所属区县在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.DistrictId = district.Id;
                        }
                        value = myArray.GetValue(1, 24);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveCompany company = new ArchiveCompany();
                        if (str.Trim() != "")
                        {
                            company = companyFacade.GetHql(str);
                            if (company == null)
                            {
                                procInfo += "该所属公司在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.CompanyId = company.Id;
                        }
                        value = myArray.GetValue(1, 25);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveStagnation stagnation = new ArchiveStagnation();
                        if (str.Trim() != "")
                        {
                            if (company != null && district != null)
                            {
                                stagnation = stagnationFacade.GetHql(str, " and CompanyId='" + company.Id + "' and DistrictId='" + district.Id + "'");
                                if (stagnation == null)
                                {
                                    procInfo += "该所属驻点不在该所属区县，所属公司内!!";
                                    isAllValid = false;
                                }
                                else
                                    entity.StagnationId = stagnation.Id;
                            }
                            else
                            {
                                procInfo += "请先填写区县跟公司,再能检查驻点!!";
                                isAllValid = false;
                            }
                        }
                        value = myArray.GetValue(1, 26);
                        DateTime? WorkDate = null;
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() != "")
                        {
                            WorkDate = ExtensionMethods.ToDateNull(value);
                            if (WorkDate == null)
                            {
                                procInfo += "工作时间格式错误!!";
                                isAllValid = false;
                            }
                        }
                        entity.WorkDate = WorkDate;
                        //工作时间可不可为空
                        value = myArray.GetValue(1, 27);
                        DateTime? EntryDate = ExtensionMethods.ToDateOANull(value);
                        if (EntryDate == null)
                        {
                            procInfo += "入职时间不可为空或格式错误!!";
                            isAllValid = false;
                        }
                        entity.EntryDate = EntryDate;
                        value = myArray.GetValue(1, 28);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "是否骨干不可为空!!";
                            isAllValid = false;
                        }
                        entity.IsBackbone = ExtensionMethods.ObjStrToShort(value);
                        value = myArray.GetValue(1, 29);
                        str = ExtensionMethods.ToStr(value);
                        entity.ItemName = str;
                        value = myArray.GetValue(1, 30);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveItemProperty itemProperty = new ArchiveItemProperty();
                        if (str.Trim() != "")
                        {
                            itemProperty = itemPropertyFacade.GetHql(str);
                            if (itemProperty == null)
                            {
                                procInfo += "该项目属性在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.ItemPropertyId = itemProperty.Id;
                        }
                        else
                        {
                            procInfo += "项目属性不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 31);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveMaintainSpecialty maintainSpecialty = new ArchiveMaintainSpecialty();
                        if (str.Trim() != "")
                        {
                            maintainSpecialty = maintainSpecialtyFacade.GetHql(str);
                            if (maintainSpecialty == null)
                            {
                                procInfo += "该维护专业在系统中不存在!!";
                                isAllValid = false;
                            }
                            else
                                entity.MaintainSpecialtyId = maintainSpecialty.Id;
                        }
                        else
                        {
                            procInfo += "维护专业不可为空!!";
                            isAllValid = false;
                        }

                        value = myArray.GetValue(1, 32);
                        str = ExtensionMethods.ToStr(value);
                        if (str.Trim() == "")
                        {
                            procInfo += "是否自维不可为空!!";
                            isAllValid = false;
                        }
                        entity.IsSelfMaintain = ExtensionMethods.ObjStrToShort(value);

                        value = myArray.GetValue(1, 33);
                        entity.Remark = ExtensionMethods.ToStr(value);
                        value = myArray.GetValue(1, 34);
                        str = ExtensionMethods.ToStr(value);
                        ArchiveGrid grid = new ArchiveGrid();
                        if (str.Trim() != "")
                        {
                            if (company != null && district != null)
                            {
                                grid = gridFacade.GetHql(str, " and CompanyId='" + company.Id + "' and DistrictId='" + district.Id + "'");
                                if (grid == null)
                                {
                                    procInfo += "该所属网格不在该所属区县，所属公司内!!";
                                    isAllValid = false;
                                }
                                else
                                    entity.GridId = grid.Id;
                            }
                            else
                            {
                                procInfo += "请先填写区县跟公司,再能检查网格!!";
                                isAllValid = false;
                            }
                        }
                    }
                    else
                    {
                        procInfo += "该人员信息在系统中已存在!!";
                        isAllValid = false;
                    }
                }
                entity.CreateUserId = userid;
                if (isAllValid)
                    isAllValid = personnelFacade.Save(entity, iFlag);
            }
            return isAllValid;
        }
        public bool CheckExcelData(string strFileName, out string procInfo, out string reFileName)
        {
            procInfo = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 34; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "AH" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.Font.Color = 255;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;
                    if (procInfo == "")
                        procInfo = "验证成功!!";
                }

                else if (i == 2)
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    try
                    {
                        isAllValid = CheckBodyData(myArray, out myText);
                    }
                    catch
                    {
                        isAllValid = false;
                    } 
                    if (myText == "")
                    {
                        myText = "验证成功!!";
                        m++;
                    }
                    else
                    {
                        isAllValid = false;
                        range.Interior.ColorIndex = 15;//背景颜色
                    }
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow -2 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/Personnel");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
            //GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }
        public bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid)
        {
            procInfo = "";
            bool isAllValid = true; 
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 34; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "AH" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.Font.Color = 255;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;

                }
                else if (i == 2)
                {
                    continue;
                }
                else
                {
                    string myText = "";
                    try
                    {
                        isAllValid = LoadBodyData(myArray, out myText, userid, i.ToString());
                    }
                    catch
                    {
                        isAllValid = false;
                    } 
                    if (isAllValid)
                    {
                        myText += "导入数据库成功";
                        m++;
                    }
                    else
                    {
                        myText += "导入数据库失败!!";
                        range.Interior.ColorIndex = 15;//背景颜色
                    }
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "数据库总导入" + (sheetRow - 2) + "条，成功" + m + "条,未成功" + (sheetRow - 2 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/Personnel");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
            //GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }  
    }
}
