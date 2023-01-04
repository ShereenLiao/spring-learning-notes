package com.code.springtransactions.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    @Update("update tb_account set money = money + #{money} where id = #{id}")
    public void transferIn(@Param("id")Integer id, @Param("money")Integer money);

    @Update("update tb_account set money = money - #{money} where id = #{id}")
    public void transferOut(@Param("id")Integer id, @Param("money")Integer money);
}
