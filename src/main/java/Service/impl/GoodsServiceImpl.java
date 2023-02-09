package Service.impl;

import DAO.GoodsDao;
import POJO.Goods;
import POJO.PageBean;
import Service.GoodsService;
import Util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author 杨梓韩
 * @version 1.0
 * @date 2023/2/3
 * @注释
 */
public class GoodsServiceImpl implements GoodsService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Goods> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodsMapper
        GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);

        //4. 调用方法
        List<Goods> goods=mapper.selectAll();

        //5. 释放资源
        sqlSession.close();

        return goods;
    }

    @Override
    public PageBean<Goods> selectByPageAndCondition(int currentPage, int pageSize, Goods goods) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodsMapper
        GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);

        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理goods条件，模糊表达式
        String goodsName = goods.getGoodsName();
        if (goodsName != null && goodsName.length() > 0) {
            goods.setGoodsName("%" + goodsName + "%");
        }

        String type = goods.getType();
        if (type != null && type.length() > 0) {
            goods.setType("%" + type + "%");
        }

        String description = goods.getDescription();
        if (description != null && description.length() > 0) {
            goods.setDescription("%" + description + "%");
        }


        //5. 查询当前页数据
        List<Goods> rows = mapper.selectByPageAndCondition(begin, size, goods);

        //6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(goods);

        //7. 封装PageBean对象
        PageBean<Goods> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //8. 释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public void add(Goods goods) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodsMapper
        GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);

        //4. 调用方法
        mapper.add(goods);
        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void delete(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodsMapper
        GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);

        //4. 调用方法
        mapper.delete(id);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodsMapper
        GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);

        //4. 调用方法
        mapper.deleteByIds(ids);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    @Override
    public void update(Goods goods) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodsMapper
        GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);

        //4. 调用方法
        mapper.update(goods);

        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }
}
