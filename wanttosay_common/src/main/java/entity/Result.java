package entity;

public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object object;


    public Result(){}

    /**
     * 使用于增删改
     * @param flag
     * @param code
     * @param message
     */

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message,Object object) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
