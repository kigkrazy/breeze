package com.reizx.breeze.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
public class PageUtils implements Serializable {
    /**
     * 获取Page对象
     *
     * @param current 当前界面
     * @param limit   每页数量
     * @param <T>     实体类型
     * @return
     */
    public static <T> Page<T> page(long current, long limit) {
        return new Page<>(current, limit);
    }

    @Data
    public static class PageWrapper {
        private long totalCount;//总记录数
        private long pageSize;//每页记录数
        private long totalPage;//总页数
        private long currPage;//当前页数
        private List<?> list;//列表数据

        /**
         * 分页
         *
         * @param page 分页
         */
        public PageWrapper(IPage<?> page) {
            this.list = page.getRecords();
            this.totalCount = page.getTotal();
            this.pageSize = page.getSize();
            this.currPage = page.getCurrent();
            this.totalPage = page.getPages();
        }
    }
}
