package com.lcl.myspring.homework.handler;

import com.lcl.myspring.homework.utils.ValueNameEnum;
import com.lcl.myspring.homework.utils.ValueNameEnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ValueNameEnum.class)
public class ValueNameEnumTypeHandler<E extends ValueNameEnum> extends BaseTypeHandler<ValueNameEnum> {

    private final Class<E> type;

    public ValueNameEnumTypeHandler(Class<E> type){
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ValueNameEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public ValueNameEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int rsInt = rs.getInt(columnName);
        return rs.wasNull() ? null : valueOf(rsInt);
    }

    @Override
    public ValueNameEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int rsInt = rs.getInt(columnIndex);
        return rs.wasNull() ? null : valueOf(rsInt);
    }

    @Override
    public ValueNameEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int rsInt = cs.getInt(columnIndex);
        return cs.wasNull() ? null : valueOf(rsInt);
    }

    private ValueNameEnum valueOf(int rsInt) {
        return ValueNameEnumUtils.valueOf(type, rsInt);
    }
}
