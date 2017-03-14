package com.mycom.products.mywebsite.core.validator.base;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.mycom.products.mywebsite.core.validator.base.ValidationOrder.First;
import com.mycom.products.mywebsite.core.validator.base.ValidationOrder.Second;
import com.mycom.products.mywebsite.core.validator.base.ValidationOrder.Third;

public interface ValidationGroup {
    interface Create {
	// validation group marker interface
    }

    interface Edit {
	// validation group marker interface
    }

    @GroupSequence({ Default.class, First.class, Second.class, Third.class })
    public interface OrderedChecks {
    }
}
