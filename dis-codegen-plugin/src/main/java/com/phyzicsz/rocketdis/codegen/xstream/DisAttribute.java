/*
 * Copyright 2019 phyzicsz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phyzicsz.rocketdis.codegen.xstream;

import com.google.common.base.Optional;
import com.phyzicsz.rocketdis.codegen.TypeMapper;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisAttributeConverter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 *
 * @author phyzicsz
 */
@XStreamAlias("attribute")
@XStreamConverter(DisAttributeConverter.class)
public class DisAttribute {

    @XStreamAsAttribute
    private Optional<String> name = Optional.absent();

    @XStreamAsAttribute
    private Optional<String> comment = Optional.absent();
    
    @XStreamAsAttribute
    private Optional<String> initialValue = Optional.absent();

    protected Optional<DisPrimitive> primitive = Optional.absent();
    
    @XStreamAlias("list")
    private Optional<DisList> list = Optional.absent();

    @XStreamAlias("fixedlist")
    private Optional<DisFixedList> fixedList = Optional.absent();
    
    @XStreamAlias("variablelist")
    private Optional<DisVariableList> variableList = Optional.absent();
    
    private Optional<DisClassRef> classRef = Optional.absent();
    
    private Optional<DisFlags> flags = Optional.absent();

    

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.fromNullable(name);
    }

    public Optional<String> getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = Optional.fromNullable(comment);
    }

    public Optional<String> getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = Optional.fromNullable(initialValue);
    }

    public Optional<DisPrimitive> getPrimitive() {
        return primitive;
    }

    public void setPrimitive(DisPrimitive primitive) {
        this.primitive = Optional.fromNullable(primitive);
    }

    public Optional<DisClassRef> getClassRef() {
        return classRef;
    }

    public void setClassRef(DisClassRef classRef) {
        this.classRef = Optional.fromNullable(classRef);
    } 

    public Optional<DisList> getList() {
        return list;
    }

    public void setList(DisList list) {
        this.list = Optional.fromNullable(list);
    }
    
    public Optional<DisFixedList> getFixedList() {
        return fixedList;
    }

    public void setFixedList(DisFixedList list) {
        this.fixedList = Optional.fromNullable(list);
    }

    public Optional<DisVariableList> getVariableList() {
        return variableList;
    }

    public void setVariableList(DisVariableList variableList) {
        this.variableList = Optional.fromNullable(variableList);
    }

    public Optional<DisFlags> getFlags() {
        return flags;
    }

    public void setFlags(DisFlags flags) {
        this.flags = Optional.fromNullable(flags);
    }
    
    public TypeName getType(){
        TypeName type = null;
        if (classRef.isPresent()) {
            type =  ClassName.bestGuess(classRef.get().getName());
        }else if (list.isPresent()){
            if(null != list.get().getClassRef()){
                type =  ClassName.bestGuess(list.get().getClassRef().getName());
            }else{
                type =  TypeMapper.typeMapper(list.get().getPrimitive());
            }
        }
        else if (variableList.isPresent()){
            type =  ClassName.bestGuess(variableList.get().getClassRef().getName());
        }
        else if(primitive.isPresent()){
            type =  TypeMapper.typeMapper(primitive.get());
        }else if(fixedList.isPresent()){
            type =  TypeMapper.typeMapper(fixedList.get().getPrimitive());
        }
        
       return type;
    }
    
    public String getTypeSize(){
        String size = "";
        if (classRef.isPresent()) {
            size = name.get() + ".wirelineSize()";
        }else if (list.isPresent()){
            if(null != list.get().getClassRef()){
                size = name.get() + ".wirelineSize()";
            }else{
                size =  TypeMapper.getSize(list.get().getPrimitive());
            }
        }
        else if (variableList.isPresent()){
            size = variableList.get().getCountFieldName() + ".wirelineSize()";
        }
        else if(primitive.isPresent()){
            size =  TypeMapper.getSize(primitive.get());
        }else if(fixedList.isPresent()){
            size =  TypeMapper.getSize(fixedList.get().getPrimitive());
        }
        
       return size;
    }
}
