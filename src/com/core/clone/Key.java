package com.core.clone;

abstract /*public*/ class Key implements Cloneable {
    private Integer id;
    private Address address;

    public Key(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    protected Key clone() throws CloneNotSupportedException {
        Key copy = (Key)super.clone();
        copy.address = address.clone();
        return copy;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", address=" + address +
                '}';
    }
}
