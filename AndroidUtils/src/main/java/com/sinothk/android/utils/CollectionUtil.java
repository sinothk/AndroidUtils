//package com.sinothk.android.utils;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//
//@Deprecated
//public class CollectionUtil {
//
//    public static void sort() {
//
////        ArrayList<UserBean> users = new ArrayList<>();
////        users.add(new UserBean("北方", "男", 30));
////        users.add(new UserBean("汕头", "女", 31));
////        users.add(new UserBean("安庆", "男", 23));
////        users.add(new UserBean("南京", "男", 45));
////
////
////        Collections.sort(users, new Comparator<UserBean>() {
////            public int compare(UserBean o1, UserBean o2) {
////                return CollectionUtil.sortChineseFirstSpell(o1.getName()).compareTo(CollectionUtil.sortChineseFirstSpell(o2.getName()));
////            }
////        });
////
////        for (UserBean i : users) {
////            System.out.print(i.getName() + "  ");
////        }
//    }
//
//    // 获取汉字的首字母大写
//    public static String sortChineseFirstSpell(String string) {
//
//        StringBuffer pybf = new StringBuffer();
//        char[] arr = string.toCharArray();
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] > 128) { //如果已经是字母就不用转换了
//                try {
//                    //获取当前汉字的全拼
//                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(
//                            arr[i], defaultFormat);
//                    if (temp != null) {
//                        pybf.append(temp[0].charAt(0));// 取首字母
//                    }
//                } catch (BadHanyuPinyinOutputFormatCombination e) {
//                    e.printStackTrace();
//                }
//            } else {
//                if (arr[i] >= 'a' && arr[i] <= 'z') {
//                    arr[i] -= 32;
//                }
//            /*if (arr[0] >= 'A' && arr[0] <= 'Z') {// 将大写转换为小写
//                arr[0] += 32;
//            }*/
//                pybf.append(arr[i]);
//            }
//        }
//        return pybf.toString();
//    }
//
//}
