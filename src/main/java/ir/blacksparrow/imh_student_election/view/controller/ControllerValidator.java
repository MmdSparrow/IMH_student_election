package ir.blacksparrow.imh_student_election.view.controller;

public class ControllerValidator {
    public static boolean isValidSizeOffset(Integer size,Integer offset){
        if((size == null && offset != null) || (size!=null && offset == null)) return false;
        else if(size != null)
            try{
                if(offset < 0)
                    return false;
                else if(size < 1)
                    return false;
            }catch (Exception e){
                return false;
            }

        return true;
    }
}
