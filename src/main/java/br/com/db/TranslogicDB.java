package br.com.db;

import lombok.extern.java.Log;
import org.jooq.DSLContext;

import static br.com.utils.PropertiesHelper.*;

@Log
public class TranslogicDB extends UtilsDB {

    private static DSLContext getDslContextTranslogic() {
        return getDslContext(
                dbTranslogicUrl,
                dbTranslogicUsername,
                dbTranslogicPassword
        );
    }

}
