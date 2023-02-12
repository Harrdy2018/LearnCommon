package com.oppo.tool.check;

import java.util.function.Supplier;

public class CheckBooleanUtil {
    @SafeVarargs
    public static boolean and(Supplier<Boolean>...suppliers){
        if(suppliers == null || suppliers.length == 0){
            return false;
        }

        for(Supplier<Boolean> supplier : suppliers){
            if (supplier == null){
                return false;
            }

            if(supplier.get() == false){
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static boolean or(Supplier<Boolean> ...suppliers){
        if(suppliers == null || suppliers.length == 0){
            return false;
        }

        for(Supplier<Boolean> supplier : suppliers){
            if (supplier == null){
                continue;
            }

            if(supplier.get() == true) {
                return true;
            }
        }
        return false;
    }
}
