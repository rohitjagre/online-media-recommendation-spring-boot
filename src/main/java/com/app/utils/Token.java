package com.app.utils;

public class Token {
    private String authKey;
    private Integer userId;

    public Token() {
        super();
    }

    public Token(String authKey, Integer id) {
        super();
        this.authKey = authKey;
        this.userId = id;
    }

    @Override
    public String toString() {
        return "Token [authKey=" + authKey + ", id=" + userId + "]";
    }

    public Integer getId() {
        return userId;
    }

    public void setId(Integer id) {
        this.userId = id;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authKey == null) ? 0 : authKey.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Token) {
            Token t = (Token) obj;
            if (t.getId().equals(userId) && t.getAuthKey().equals(authKey)) {
                return true;
            }
        }
        return false;
    }

}
