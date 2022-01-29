package com.colson.util.excel;

import com.colson.util.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ExcelDemo {
    /**
     * @brief 支付通道-导出
     * @api GET /api/payment/pay-routing/query-payment-channels-by-page-export.json
     * @occurs songbowen
     */
//    @RequestMapping(value ="/query-payment-channels-by-page-export.json",method = RequestMethod.GET)
//    @RequiresPermissions(PermissionConstant.PAYMENT_QUERY_PAYMENT_CHANNELS_EXPORT)
    public void queryPaymentChannelsByPageExport(HttpServletResponse response, String request) {
//        List<ChannelInfoResDTO> channelInfoResDTOS = httpRequestService.queryPaymentChannelsByPageExport(request);
        List<String> result = new ArrayList<>();
		HSSFWorkbook workbook;
        try {
            workbook = (HSSFWorkbook) ExlExport.exportExcel(result,"ChannelInfoResDTO");
            ExlExport.printExcel(workbook, response, "支付通道列表"+ DateUtils.getDateTime()+".xlsx");
        } catch (Exception e) {
//            logger.error("系统异常"+e);
        }
    }
}
