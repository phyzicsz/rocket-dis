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

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.Optional;
import javax.lang.model.element.Modifier;

/**
 *
 * @author phyzicsz
 */
public class DisList extends AbstractAttribute implements AbstractAttributeCodeGeneration{
    
    @XStreamAsAttribute
    private String type;
    
    @XStreamAsAttribute
    private Integer length;
    
    @XStreamAsAttribute
    private Boolean couldBeString;
    
    private Optional<DisPrimitive> primitive = Optional.empty();   
    
    private Optional<DisClassRef> classRef = Optional.empty();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getCouldBeString() {
        return couldBeString;
    }

    public void setCouldBeString(Boolean couldBeString) {
        this.couldBeString = couldBeString;
    }

    public Optional<DisPrimitive> getPrimitive() {
        return primitive;
    }

    public void setPrimitive(DisPrimitive primitive) {
        this.primitive = Optional.ofNullable(primitive);
    } 

    public Optional<DisClassRef> getClassRef() {
        return classRef;
    }

    public void setClassRef(DisClassRef classRef) {
        this.classRef = Optional.ofNullable(classRef);
    }

    @Override
    public Optional<TypeName> typeName() {
        if (classRef.isPresent()) {
            return classRef.get().typeName();
        } else if (primitive.isPresent()){
            return primitive.get().typeName();
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> typeSize() {
        if (classRef.isPresent()) {
            return classRef.get().typeSize();
        } else if (primitive.isPresent()){
            return primitive.get().typeSize();
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<FieldSpec> fieldSpec() {

        Optional<TypeName> typeNameOptional = typeName();
        if (typeNameOptional.isEmpty()) {
            return Optional.empty();
        }
        TypeName type = typeNameOptional.get();
        String name = getName().get();
        FieldSpec.Builder field = FieldSpec.builder(type, name)
                .addModifiers(Modifier.PROTECTED);
        if (!type.isPrimitive()) {
            field.initializer("new $T()", type);
        } else {
            field.initializer("new $T[$L]", type, length);
        }
        return Optional.of(field.build());
    }
    
}
