package com.kin.web.myphoto.global.TypeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedTypes(LocalDateTime.class)
public class LocalDateTimeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i,localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return LocalDateTime.ofEpochSecond(resultSet.getLong(s)/1000,0,ZoneOffset.ofHours(8));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return LocalDateTime.ofEpochSecond(resultSet.getLong(i)/1000,0,ZoneOffset.ofHours(8));
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return LocalDateTime.ofEpochSecond(callableStatement.getLong(i)/1000,0,ZoneOffset.ofHours(8));
    }
}
