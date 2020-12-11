package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.zhanghf.dto.CommonDTO;
import com.zhanghf.enums.BusinessCodeEnum;
import com.zhanghf.vo.ResultVo;
import com.zhanghf.vo.excel.ExportExcelVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:03 2020/11/27
 */
@Slf4j
public class ExportExcelUtils {

    /**
     * excel通用导出
     *
     * @param uuid     唯一识别码
     * @param response HttpServletResponse
     * @param vos      标题
     * @param data     导出内容
     */
    public static ResultVo<List<Map<String, String>>> exportExcelCommon(String uuid, HttpServletResponse response, List<ExportExcelVO> vos, List<Map<String, String>> data) {
        ResultVo<List<Map<String, String>>> resultVo = new ResultVo<>(uuid);
        log.info("uuid={}, vos={}, data={}", uuid, vos, data);
        String fileName = uuid + ".xls";
        try (
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
                FileOutputStream fileOutputStream = new FileOutputStream(fileName)
        ) {
            //创建sheet
            HSSFSheet sheet = hssfWorkbook.createSheet();
            hssfWorkbook.setSheetName(0, "San");
            int dataSize = data.size();
            for (int i = 0; i < dataSize + 1; i++) {
                HSSFRow row = sheet.createRow(i);
                int mapSize = vos.size();
                for (int j = 0; j < mapSize; j++) {
                    if (i == 0) {
                        sheet.setColumnWidth(j, 256 * vos.get(j).getColumnWidth() + 185);
                        HSSFCell hssfCell = row.createCell(j);
                        hssfCell.setCellStyle(setRowStyle(hssfWorkbook));
                        hssfCell.setCellValue(vos.get(j).getNameValue());
                    } else {
                        HSSFCell hssfCell = row.createCell(j);
                        String keyValue = vos.get(j).getKeyValue();
                        Map<String, String> map = data.get(i - 1);
                        Object value = map.get(keyValue);
                        if (value != null) {
                            String simpleName = value.getClass().getSimpleName();
                            switch (simpleName) {
                                case "String":
                                    hssfCell.setCellValue(value.toString());
                                    break;
                                case "Long":
                                    hssfCell.setCellValue(JSON.toJSONString(value));
                                    break;
                                default:
                                    log.info("uuid={}, value={}, simpleName={}", uuid, value, value.getClass().getSimpleName());
                                    hssfCell.setCellValue(value.toString());
                                    break;
                            }
                        }
                    }
                }
            }
            hssfWorkbook.write(fileOutputStream);
            response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, CommonDTO.CHARSET_NAME));
        } catch (Exception e) {
            log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            resultVo.setResultDes(BusinessCodeEnum.UNKNOWN_ERROR.getMsg());
            resultVo.setCode(BusinessCodeEnum.UNKNOWN_ERROR.getCode());
            return resultVo;
        }
        try (
                OutputStream outputStream = response.getOutputStream();
                InputStream inputStream = new FileInputStream(fileName)
        ) {
            byte[] b = new byte[4096];
            int size = inputStream.read(b);
            while (size > 0) {
                outputStream.write(b, 0, size);
                size = inputStream.read(b);
            }
        } catch (IOException e) {
            log.error(CommonDTO.COMMON_LOGGER_ERROR_INFO_PARAM, uuid, CommonUtils.getStackTraceString(e));
            resultVo.setResultDes(BusinessCodeEnum.UNKNOWN_ERROR.getMsg());
            resultVo.setCode(BusinessCodeEnum.UNKNOWN_ERROR.getCode());
            return resultVo;
        } finally {
            File file = new File(fileName);
            if (file.exists()) {
                log.info("uuid={}, flag={}", uuid, file.delete());
            }
        }
        resultVo.setResultDes(BusinessCodeEnum.SUCCESS.getMsg());
        resultVo.setCode(BusinessCodeEnum.SUCCESS.getCode());
        resultVo.setSuccess(true);
        return resultVo;
    }

    public static HSSFCellStyle setRowStyle(HSSFWorkbook hssfWorkbook) {
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(setRowFont(hssfWorkbook));
        //左右居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //上下居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private static HSSFFont setRowFont(HSSFWorkbook hssfWorkbook) {
        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName("仿宋_GB2312");
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        return font;
    }
}
