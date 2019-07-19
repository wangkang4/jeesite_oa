package com.thinkgem.jeesite.modules.CostExcel.utils;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.CostExcel.entity.CostRecondExcel;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {

    private final static String excel2003L = ".xls";// 2003- 版本的excel
    private final static String excel2007U = ".xlsx";// 2007+ 版本的excel
    /**
     * 错误信息
     */
    public static String ERROR_INFO;

    /**
     * @描述：判断Excle是否是03版本
     * @作者：王东
     * @参数：filePath 文件路径
     * @返回值：boolean
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches ( "^.+\\.(?i)(xls)$" );
    }

    /**
     * @描述：判断Excle是否是07版本
     * @作者：王东
     * @参数：filePath 文件路径
     * @返回值：boolean
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches ( "^.+\\.(?i)(xlsx)$" );
    }

    /**
     * @描述：验证excel文件
     * @作者：王东
     * @参数：filePath 文件路径
     * @返回值：boolean
     */
    public static boolean validateExcel(String filePath) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null || !(ExcelUtil.isExcel2003 ( filePath ) || ExcelUtil.isExcel2007 ( filePath ))) {
            ERROR_INFO = "文件名不是excel格式";
            return false;
        }
        /** 检查文件是否存在 */
        File file = new File ( filePath );
        if (file == null || !file.exists ()) {
            ERROR_INFO = "文件不存在";
            return false;
        }
        return true;
    }

    /**
     * 解析excel格式
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public boolean getExcelFormat(String fileName) throws Exception {

        String fileType = fileName.substring ( fileName.lastIndexOf ( "." ) );
        if (excel2003L.equals ( fileType )) {
            return true;
        } else if (excel2007U.equals ( fileType )) {
            return false;
        } else {
            throw new Exception ( "解析的文件格式有误！" );
        }
    }

    /**
     * 通用批量插入
     *
     * @param map   excel多个sheet的集合
     * @param sheet sheet名称
     * @param type  指定的类型
     * @return
     * @throws ParseException
     */
    public static List<CostRecondExcel> commonInsert(Map<String, List<List<String>>> map, String sheet, int type) throws ParseException {
        User user = UserUtils.getUser ();
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
        List<List<String>> list = map.get ( sheet );// 获取指定sheet的List<List<String>>

        List<CostRecondExcel> costRecondExcellist = new ArrayList<CostRecondExcel> ();// 包装对象的集合

        for (int i = 1; i < list.size (); i++) {// 从第二行开始
            List<String> lo = list.get ( i );
            CostRecondExcel CostRecondExcel = new CostRecondExcel ();
            CostRecondExcel.setId ( IdGen.uuid () );
            CostRecondExcel.setClientName ( String.valueOf ( lo.get ( 0 ) ) );
            CostRecondExcel.setProjectName ( String.valueOf ( lo.get ( 1 ) ) );
            CostRecondExcel.setProjectMoney ( String.valueOf ( lo.get ( 2 ) ) );
//			Cell c00 = st.getCell(i,3);
//			CostRecondExcel.setUseTime();
            System.out.println ( ">>>>>>>>>>>>>>>>>>" + String.valueOf ( lo.get ( 3 ) ) );
            CostRecondExcel.setUseTime ( sdf.parse ( String.valueOf ( lo.get ( 3 ) ) ) );
            CostRecondExcel.setTravelExpense ( String.valueOf ( lo.get ( 4 ) ) );
            CostRecondExcel.setMealMoney ( String.valueOf ( lo.get ( 5 ) ) );
            CostRecondExcel.setCulturalgiftsPerson ( String.valueOf ( lo.get ( 6 ) ) );
            CostRecondExcel.setType ( type );
            CostRecondExcel.setOtherMoney ( String.valueOf ( lo.get ( 7 ) ) );
            CostRecondExcel.setCulturalgiftsCompeny ( String.valueOf ( lo.get ( 8 ) ) );
            CostRecondExcel.setConment ( String.valueOf ( lo.get ( 9 ) ) );
            CostRecondExcel.setCreaterBy ( user );
            CostRecondExcel.setCreaterTime ( new Date () );
            costRecondExcellist.add ( CostRecondExcel );
        }
        return costRecondExcellist;
    }

    /**
     * 重新命名
     *
     * @param fileName 文件原名
     * @return 重命名后的名字
     */
    public static String rename(String fileName) {
        String fx = fileName.substring ( fileName.lastIndexOf ( "." ) + 1 );
        Date now = new Date ();
        SimpleDateFormat myFmt2 = new SimpleDateFormat ( "yyyyMMddhhmmss" );
        StringBuilder fileRename = new StringBuilder ();
        fileRename.append ( myFmt2.format ( now ) );
        fileRename.append ( System.currentTimeMillis () );
        // 随机生成6位字母
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random ();
        StringBuffer sb = new StringBuffer ();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt ( base.length () );
            sb.append ( base.charAt ( number ) );
        }
        fileRename.append ( sb.toString () + "." + fx );
        return fileRename.toString ();
    }

    //检查excel内必要项
//	public static Object checkCommonInsert(Map<String, List<List<String>>> map, String sheet) {
//		
//		List<List<String>> list = map.get(sheet);// 获取指定sheet的List<List<String>>
//		if(list == null || list.size() < 1){
//			return "ok";
//		}
//		
//		String clientName = "";
//		String projectName = "";
//		for (int i = 1; i < list.size(); i++) {
//			List<String> lo = list.get(i);
//			clientName = String.valueOf(lo.get(0));
//			projectName = String.valueOf(lo.get(1));
//			if (StringUtils.isNotBlank(clientName)) {
//				int clientcount = clientService.isexits(clientName);
//				if (clientcount == 0) {
//					return "errror2";
//				}
//				if (StringUtils.isNotBlank(projectName)) {
//					int projectcount = projectService.isexits(projectName);
//					if (projectcount == 0) {
//						return "error4";
//					}
//				} else {
//					return "error3";
//				}
//			} else {
//				return "error1";
//			}
//		}
////		if("技术".equals(sheet)){
////			for (int i = 1; i < list.size(); i++) {
////				List<String> lo = list.get(i);
////				clientName = String.valueOf(lo.get(0));
////				projectName = String.valueOf(lo.get(1));
////				if(StringUtils.isNotBlank(clientName)){
////					int clientcount = clientService.isexits(clientName);
////					if(clientcount==0){
////						return "errror2";
////					}
////					if(StringUtils.isNotBlank(projectName)){
////						int projectcount = projectService.isexits(projectName);
////						if(projectcount==0){
////							return "error4";
////						}
////					}else{
////						return "error3";
////					}
////				}else{
////					return "error1";
////				}
////			}
////		}
////		if("行政".equals(sheet)){
////			for (int i = 1; i < list.size(); i++) {
////				List<String> lo = list.get(i);
////				clientName = String.valueOf(lo.get(0));
////				projectName = String.valueOf(lo.get(1));
////				if(StringUtils.isNotBlank(clientName)){
////					int clientcount = clientService.isexits(clientName);
////					if(clientcount==0){
////						return "errror2";
////					}
////					if(StringUtils.isNotBlank(projectName)){
////						int projectcount = projectService.isexits(projectName);
////						if(projectcount==0){
////							return "error4";
////						}
////					}else{
////						return "error3";
////					}
////				}else{
////					return "error1";
////				}
////			}
////		}
//		
////		if ("销售".equals(sheet)) {
////			for (int i = 1; i < list.size(); i++) {
////				List<String> lo = list.get(i);
////				if (StringUtils.isNotBlank(String.valueOf(lo.get(0)))
////						&& StringUtils.isNotBlank(String.valueOf(lo.get(1)))
////						&& StringUtils.isNotBlank(String.valueOf(lo.get(2)))
////						&& StringUtils.isNotBlank(String.valueOf(lo.get(3)))) {
////				} else {
////					if(StringUtils.isNotBlank(String.valueOf(lo.get(0)))
////							&& StringUtils.isNotBlank(String.valueOf(lo.get(1)))
////							&& StringUtils.isNotBlank(String.valueOf(lo.get(2)))
////							&& StringUtils.isBlank(String.valueOf(lo.get(3)))){
////						return "error4";
////					}
////					return "error1";
////				}
////			}
////		}
////		if ("技术".equals(sheet)) {
////			for (int i = 1; i < list.size(); i++) {
////				List<String> lo = list.get(i);
////				if (StringUtils.isNotBlank(String.valueOf(lo.get(0)))
////						&& StringUtils.isNotBlank(String.valueOf(lo.get(1)))
////						&& StringUtils.isNotBlank(String.valueOf(lo.get(3)))) {
////				} else {
////					if(StringUtils.isNotBlank(String.valueOf(lo.get(0)))
////							&& StringUtils.isNotBlank(String.valueOf(lo.get(1)))
////							&& StringUtils.isBlank(String.valueOf(lo.get(3)))){
////						return "error4";
////					}
////					return "error2";
////				}
////			}
////		}
////		if ("行政".equals(sheet)) {
////			for (int i = 1; i < list.size(); i++) {
////				List<String> lo = list.get(i);
////				if (StringUtils.isNotBlank(String.valueOf(lo.get(0)))
////						&& StringUtils.isNotBlank(String.valueOf(lo.get(1)))
////						&& StringUtils.isNotBlank(String.valueOf(lo.get(3)))) {
////				} else {
////					if(StringUtils.isNotBlank(String.valueOf(lo.get(0)))
////							&& StringUtils.isNotBlank(String.valueOf(lo.get(1)))
////							&& StringUtils.isBlank(String.valueOf(lo.get(3)))){
////						return "error4";
////					}
////					return "error3";
////				}
////			}
////		}
//		return "ok";
//	}

}
