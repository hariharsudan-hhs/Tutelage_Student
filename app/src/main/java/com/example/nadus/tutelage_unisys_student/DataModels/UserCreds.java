package com.example.nadus.tutelage_unisys_student.DataModels;

import java.io.Serializable;

/**
 * Created by msuba on 12/31/2017.
 */

public class UserCreds implements Serializable
{
    public UserCreds(){}
    public static String Uname;
    public static String Umail;
    public static String UDOB;
    public static String Uinstitution;
    public static String Ucontact;
    public static String Upass;
    public static String Ulocation;
    public static String Uregno;
    public static String Uclassname;

    public String getUclassname() {
        return Uclassname;
    }

    public void setUclassname(String uclassname) {
        Uclassname = uclassname;
    }

    public String getUregno() {
        return Uregno;
    }

    public void setUregno(String uregno) {
        Uregno = uregno;
    }

    public String getUlocation() {
        return Ulocation;
    }

    public void setUlocation(String ulocation) {
        Ulocation = ulocation;
    }

    public String getUpass() {
        return Upass;
    }

    public void setUpass(String upass) {
        Upass = upass;
    }

    public String getUcontact() {
        return Ucontact;
    }

    public void setUcontact(String ucontact) {
        Ucontact = ucontact;
    }


    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getUmail() {
        return Umail;
    }

    public void setUmail(String umail) {
        Umail = umail;
    }

    public String getUDOB() {
        return UDOB;
    }

    public void setUDOB(String UDOB) {
        this.UDOB = UDOB;
    }

    public String getUinstitution() {
        return Uinstitution;
    }

    public void setUinstitution(String uinstitution) {
        Uinstitution = uinstitution;
    }
}
