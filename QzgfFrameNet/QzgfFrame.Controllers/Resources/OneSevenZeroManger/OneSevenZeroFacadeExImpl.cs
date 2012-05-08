using System;
using System.Data;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.OneSevenZeroManger.Domain;
using QzgfFrame.Resources.OneSevenZeroManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Resources.ClieEquipManger.Domain;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Resources.OneSevenZeroManger
{
    public class OneSevenZeroFacadeExImpl : OneSevenZeroFacadeEx
    {
        private OneSevenZeroFacade oneSevenZeroFacade { set; get; }
        private ClieEquipFacade clieEquipFacade { set; get; }
        private FactoryFacade factoryFacade { set; get; }
        private EquipTypeFacade equipTypeFacade { set; get; }
        private EquipModelFacade equipModelFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(ResourceOneSevenZero entity)
        {
            bool result = false;
                result = this.oneSevenZeroFacade.Save(entity,"0");
               
            if(result==false)
                throw new Exception("操作失败!!");
                return result;           
        }
        public bool Update(ResourceOneSevenZero entity)
        {
            bool result = false;
            result = this.oneSevenZeroFacade.Update(entity);
           
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
    }
}
