using System.Data;
using System.Collections.Generic;
using QzgfFrame.Resources.OneSevenZeroManger.Domain;
using QzgfFrame.Resources.OneSevenZeroManger.Models;

namespace QzgfFrame.Controllers.Resources.OneSevenZeroManger

{
    public interface OneSevenZeroFacadeEx
    {
        bool Save(ResourceOneSevenZero entity);
        bool Update(ResourceOneSevenZero entity);
    }
}
