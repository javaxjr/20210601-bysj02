package com.tjetc.sftp.dao;

import com.tjetc.sftp.entity.BusCard;

import java.util.List;

public interface BusCardDao {
    public void saveAll(List<BusCard> busCardList);
}
