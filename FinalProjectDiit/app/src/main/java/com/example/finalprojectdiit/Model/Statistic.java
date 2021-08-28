package com.example.finalprojectdiit.Model;

public class Statistic {
    public int cs_count_result,ite_count_result,cmt_count_result,cgm_count_result,ini_count_result,inter_count_result;

    public Statistic() {
    }

    public Statistic(int cs_count_result, int ite_count_result, int cmt_count_result, int cgm_count_result, int ini_count_result, int inter_count_result) {
        this.cs_count_result = cs_count_result;
        this.ite_count_result = ite_count_result;
        this.cmt_count_result = cmt_count_result;
        this.cgm_count_result = cgm_count_result;
        this.ini_count_result = ini_count_result;
        this.inter_count_result = inter_count_result;
    }

    public int getCs_count_result() {
        return cs_count_result;
    }

    public void setCs_count_result(int cs_count_result) {
        this.cs_count_result = cs_count_result;
    }

    public int getIte_count_result() {
        return ite_count_result;
    }

    public void setIte_count_result(int ite_count_result) {
        this.ite_count_result = ite_count_result;
    }

    public int getCmt_count_result() {
        return cmt_count_result;
    }

    public void setCmt_count_result(int cmt_count_result) {
        this.cmt_count_result = cmt_count_result;
    }

    public int getCgm_count_result() {
        return cgm_count_result;
    }

    public void setCgm_count_result(int cgm_count_result) {
        this.cgm_count_result = cgm_count_result;
    }

    public int getIni_count_result() {
        return ini_count_result;
    }

    public void setIni_count_result(int ini_count_result) {
        this.ini_count_result = ini_count_result;
    }

    public int getInter_count_result() {
        return inter_count_result;
    }

    public void setInter_count_result(int inter_count_result) {
        this.inter_count_result = inter_count_result;
    }
}
