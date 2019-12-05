package com.shun.dao;

import com.shun.entity.Music;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface MusicDao extends Mapper<Music>, DeleteByIdListMapper<Music,String> {
}
