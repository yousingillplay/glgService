//$Id: Lot.java,v 1.7 2012/07/11 22:45:06 a0199948 Exp $
package com.ti.techmania.glgservice.domain;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Lot data transfer object.
 * TODO: remove (demonstration only)
 */
public class Lot implements Serializable {
    //---- Members
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @NotNull
    private String lot;
    @NotNull
    @Size(min=4, max=4, message="Logpoint must be four characters long")
    private String lpt;
    @Min(value=1, message="Current quantity must be greater than one")
    @Max(value=25, message="Current quantity must be less than 25")
    private int curQty;

    //---- Methods
    public int getCurQty() {
        return curQty;
    }

    public void setCurQty(final int curQty) {
        this.curQty = curQty;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(final String lot) {
        this.lot = lot;
    }

    public String getLpt() {
        return lpt;
    }

    public void setLpt(final String lpt) {
        this.lpt = lpt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
