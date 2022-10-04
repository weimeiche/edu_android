package com.navyliu.widget.unit3Lsn4RecyclerView;

class RecyclerBean {
    
    private Integer src;
    private String  username, remark;

    public String getUsername() {
        return username;
    }

    public RecyclerBean setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getSrc() {
        return src;
    }

    public RecyclerBean setSrc(Integer src) {
        this.src = src;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public RecyclerBean setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
