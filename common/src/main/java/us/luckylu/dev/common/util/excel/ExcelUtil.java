package us.luckylu.dev.common.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author lu
 * @create 2019-03-01 17:30
 */
@Slf4j
public class ExcelUtil {

    public static <T> void export(Class<T> clazz, List<T> list, ServletOutputStream outputStream) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {
            fillWorkbook(clazz, list, workbook);
            // 转换为输出流
            workbook.write(outputStream);
        } catch (Exception e) {
            // 不抛异常，因为前端直接下载
            log.error("Excel导出失败，错误原因是{}，堆栈是{}", e.getMessage(), e.getStackTrace());
        }

    }

    /**
     * 创建工作簿标题、表格标题、填充表格数据
     */
    private static <T> void fillWorkbook(Class<T> clazz, List<T> list, HSSFWorkbook workbook) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        String sheetName = "xxx表";
        String headName = "xxx表";
        Integer fieldNum = 1;

        // 按指定顺序获取属性自定义的标题
        Map<Integer, String> headMap = new TreeMap<>();
        // 按指定顺序获取属性自定义的宽度
        Map<Integer, Integer> widthMap = new TreeMap<>();
        // 获取属性在Bean中定义的顺序
        List<Integer> sequences = new ArrayList<>();
        // 转为List<Map<>> 可根据顺序取出对应属性的实际值
        List<Map<Integer, String>> dataList = new ArrayList<>();

        // 获取表格与sheet名
        if(clazz.isAnnotationPresent(ExcelSheet.class)){
            ExcelSheet sheet = clazz.getAnnotation(ExcelSheet.class);
            headName = sheet.headName();
            sheetName = sheet.sheetName();

            // 获取所有声明的字段及总数，包括protected、private，不包括父类的字段
            Field[] fields = clazz.getDeclaredFields();
            fieldNum = fields.length;

            // 遍历字段，获取原顺序，自定义顺序-标题，自定义顺序-宽度
            for (Field field : fields) {
                if(field.isAnnotationPresent(ExcelColumn.class)){
                    ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                    headMap.put(column.sequence(), column.head());
                    widthMap.put(column.sequence(), column.width());
                    sequences.add(column.sequence());
                }

                // TODO 转换
                if(field.isAnnotationPresent(ColumnType.class)){
                    ColumnType columnType = field.getAnnotation(ColumnType.class);
                    if(columnType.isInteger()){

                        Class<? extends Enum> enumClass = columnType.convertEnum();
                        try {
                            Enum intEnum = enumClass.newInstance();
                            Method[] methods = enumClass.getDeclaredMethods();
                            for (Method method : methods) {
                                // 如果有该方法
                                if(Objects.equals(method.getName(), "getMsg")){
                                    method.invoke(intEnum);
                                }

                            }
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }

            // 将原有的list遍历，属性按Bean的原顺序放入TreeMap，使得map可以按指定顺序取值
            for (T element : list) {
                Map<Integer, String> dataMap = new TreeMap<>();

                for(int i = 0 ; i < fieldNum; i++){
                    // 将顺序和属性名对应上，利用反射获取方法，调用方法获取值
                    Method readMethod = new PropertyDescriptor(fields[i].getName(), clazz).getReadMethod();
                    Object invoke = readMethod.invoke(element);
                    dataMap.put(sequences.get(i), invoke == null ? "" : invoke.toString());
                }
                dataList.add(dataMap);
            }
        }

        HSSFSheet sheet = workbook.createSheet(sheetName);
        createHeadTittle(workbook, sheet, headName, fieldNum - 1);
        createTableHead(workbook, sheet, headMap, widthMap);
        createTable(workbook, sheet, dataList, fieldNum);
    }

    /**
     * 创建表格，按照list在单元格中填充数据
     */
    private static void createTable(HSSFWorkbook workbook, HSSFSheet sheet,
                                        List<Map<Integer, String>> dataList, int fieldNum) {
        // 定义单元格格式，添加单元格表样式，并添加到工作薄
        HSSFCellStyle cellStyle = setTableStyle(workbook);

        // 遍历列，循环插入数据
        for (int i = 0; i < dataList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 2);
            // 设置高度
            row.setHeight((short) 500);

            Map<Integer, String> dataMap = dataList.get(i);
            for (int j = 0; j < fieldNum; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(dataMap.get(j));
                // TODO 格式转换
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        cell.setCellValue(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        //如果为时间格式的内容
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            //cell.setCellValue(DateTimeUtil.formatDateByCell(cell.getDateCellValue()));
                        }else {
                            // 如果是一般数字，保留两位
                            String decimalPattern = "#.##";
                            // 如果是整数
                            if ((int)(cell.getNumericCellValue()) == cell.getNumericCellValue()) {
                                decimalPattern = "#";
                            }
                            cell.setCellValue(new DecimalFormat(decimalPattern).format(cell.getNumericCellValue()));
                        }
                        break;
                    case BOOLEAN:
                        cell.setCellValue(cell.getBooleanCellValue());
                        break;
                    case BLANK:
                        cell.setCellValue("");
                        break;
                    default:
                        cell.setCellValue(cell.toString());
                        break;
                }
                cell.setCellStyle(cellStyle);
            }
        }

    }

    /**
     * 设置表格单元格样式
     */
    private static HSSFCellStyle setTableStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);

        // 单元格字体
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 创建表格标题
     */
    private static void createTableHead(HSSFWorkbook workbook, HSSFSheet sheet,
                                        Map<Integer, String> headMap, Map<Integer, Integer> widthMap) {
        HSSFRow rowOfHead = sheet.createRow(1);
        HSSFCellStyle cellStyle = setTableHeadStyle(workbook, rowOfHead);

        // 设置表头内容
        for (int i = 0; i < headMap.size(); i++) {
            HSSFCell cell = rowOfHead.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(new HSSFRichTextString(headMap.get(i)));
            cell.setCellStyle(cellStyle);
        }
        // 设置每一列宽度
        for (int i = 0; i < widthMap.size(); i++) {
            sheet.setColumnWidth(i, widthMap.get(i));
        }
    }

    /**
     * 定义表格标题的样式
     */
    private static HSSFCellStyle setTableHeadStyle(HSSFWorkbook workbook, HSSFRow row) {
        row.setHeight((short) 600);

        // 定义单元格格式，添加单元格表样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        // 指定单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 指定单元格垂直居中个对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置背景色
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置右边框类型
        cellStyle.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色
        cellStyle.setRightBorderColor(IndexedColors.GREY_25_PERCENT.index);
        // 设置单元格字体
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 创建工作簿标题
     */
    private static void createHeadTittle(HSSFWorkbook workbook, HSSFSheet sheet, String head, int col) {
        // 创建Excel工作表的行
        HSSFRow row = sheet.createRow(0);
        // 设置高度
        row.setHeight((short) 1000);

        // 创建Excel工作表指定行的单元格
        HSSFCell cell = row.createCell(0);
        // 定义单元格为字符串类型
        cell.setCellType(CellType.STRING);
        cell.setCellValue(new HSSFRichTextString(head));

        // 指定标题合并区域
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, col));
        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        setHeadTittleStyle(workbook, cell);
    }

    /**
     * 设置工作簿标题样式
     */
    private static void setHeadTittleStyle(HSSFWorkbook workbook, HSSFCell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 指定单元格垂直居中个对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 指定单元格自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        // 字体大小
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

}
