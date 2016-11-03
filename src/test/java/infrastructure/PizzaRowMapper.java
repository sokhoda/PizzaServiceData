package infrastructure;

import domain.Pizza;
import domain.PizzaType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pizza pizza = new Pizza();
        pizza.setPizzaId(rs.getLong("pizzaId"));
        pizza.setName(rs.getString("name"));
        pizza.setPrice(rs.getDouble("price"));
        pizza.setType(PizzaType.valueOf(rs.getString("type")));
        return pizza;
    }
}
