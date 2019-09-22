package com.example.administrator.myp2p.util;

public class StringUtils {
    public static String getCourseById(int Id){
        switch (Id){
            case 40: return "专业英语"                         ;
            case  3: return "信息化与信息系统·第一节生命周期"  ;
            case 30: return "信息文档管理与配置管理"           ;
            case 18: return "信息系统项目管理基础"             ;
            case 36: return "外包管理"                         ;
            case 33: return "战略管理"                        ;
            case 35: return "流程管理"                         ;
            case 31: return "知识管理、知识产权与标准规范"     ;
            case 118: return "第一章 · 第七节 大型信息系统"     ;
            case 114: return "第一章 · 第三节 信息系统基础"     ;
            case 113: return "第一章 · 第二节 计算机网络知识"   ;
            case 116: return "第一章 · 第五节 信息安全知识"     ;
            case 117: return "第一章 · 第六节 信息系统工程监理" ;
            case 115: return "第一章 · 第四节 IT服务、云计算"   ;
            case 39: return "管理科学、运筹学"                 ;
            case 34: return "组织级项目管理"                   ;
            case 38: return "职业道德"                         ;
            case 37: return "需求管理"                         ;
            case 25: return "项目人力资源管理"                 ;
            case 32: return "项目变更管理、项目整体绩效评估"   ;
            case 29: return "项目合同管理"                     ;
            case 23: return "项目成本管理"                     ;
            case 20: return "项目整体管理"                     ;
            case 26: return "项目沟通管理和干系人管理"         ;
            case 19: return "项目立项管理"                     ;
            case 21: return "项目范围管理"                     ;
            case 24: return "项目质量管理"                     ;
            case 22: return "项目进度管理"                     ;
            case 28: return "项目采购管理"                     ;
            case 27: return "项目风险管理"                     ;
            default:
                return "空";
        }
    }
}
