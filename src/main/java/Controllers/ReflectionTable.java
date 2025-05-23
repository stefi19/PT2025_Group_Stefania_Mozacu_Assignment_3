package Controllers;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import java.lang.reflect.Method;
import java.util.List;
public class ReflectionTable {
    public static <T> void populateTable(TableView<T> table, List<T> items)
    {
        if (items==null||items.isEmpty())
            return;
        table.getColumns().clear();
        Class<?> cl=items.get(0).getClass();
        for (Method method:cl.getMethods())
        {
            String methodName=method.getName();
            if ((methodName.startsWith("get")&&!methodName.equals("getClass")))
            {
                String propertyName;
                propertyName=methodName.substring(3);
                TableColumn<T, String> col = makeStringForColumnName(method, propertyName);
                table.getColumns().add(col);
            }
        }
        table.getItems().setAll(items);
    }

    private static <T> TableColumn<T, String> makeStringForColumnName(Method method, String propertyName) {
        TableColumn<T, String> col=new TableColumn<>(propertyName);
        col.setCellValueFactory(cellData -> { T rowValue=cellData.getValue();
            try {
                Object value= method.invoke(rowValue);
                String stringValue;
                if (value!=null)
                {
                    stringValue = value.toString();
                }
                else
                {
                    stringValue = "";
                }
                return new SimpleStringProperty(stringValue);
            } catch (Exception e) {
                e.printStackTrace();
                return new SimpleStringProperty("");
            }
        });
        return col;
    }
}
