using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Utility.Business
{
    public interface IRepository<T>
        where T: EntityBase ,new()
    {
        /// <summary>
        /// 获取实体
        /// </summary>
        /// <param name="id">主键值</param>
        /// <returns></returns>
        T GetEntity(object id);

        /// <summary>
        /// 上次实体
        /// </summary>
        /// <param name="entity">被删除的实体</param> 
        void DeleteEntity(T entity);

        /// <summary>
        /// 增加实体
        /// </summary>
        /// <param name="entity">被更新的对象</param>
        object AddEntity(T entity);

        /// <summary>
        /// 更新实体
        /// </summary>
        /// <param name="entity">被更新的对象</param>
        void UpdateEntity(T entity);

        /// <summary>
        /// 更新实体
        /// </summary>
        /// <param name="entity">被更新的对象</param>
        void SaveEntity(T entity);
    }
}
