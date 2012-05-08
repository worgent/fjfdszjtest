
namespace QzgfFrame.Webservice.Service
{
    public interface WsenterFacade
    {
        /// <summary>
        /// 170数据导入
        /// </summary>
        /// <param name="datajson">170数据的json格式</param>
        /// <returns>true:保存成功,false:保存失败</returns>
        bool Import170(string datajson);

    }
}
