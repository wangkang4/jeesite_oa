package com.thinkgem.jeesite.modules.receiptNotice.entity;

import java.io.Serializable;

public class AjaxEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((count == null) ? 0 : count.hashCode ());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass () != obj.getClass ())
            return false;
        AjaxEntity other = (AjaxEntity) obj;
        if (count == null) {
            if (other.count != null)
                return false;
        } else if (!count.equals ( other.count ))
            return false;
        return true;
    }

}
