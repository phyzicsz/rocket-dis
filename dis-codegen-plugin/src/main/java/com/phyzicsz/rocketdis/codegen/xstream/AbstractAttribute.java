/*
 * Copyright 2020 phyzicsz.
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
public abstract class AbstractAttribute {
    @XStreamAsAttribute
    private Optional<String> name = Optional.empty();

    @XStreamAsAttribute
    private Optional<String> comment = Optional.empty();
    
     public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public Optional<String> getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = Optional.ofNullable(comment);
    }
    
    public abstract Optional<TypeName> typeName();
    
    public abstract Optional<String> typeSize();
    
    public abstract Optional<FieldSpec> fieldSpec();
    
    public Optional<MethodSpec> getterSpec(FieldSpec spec) {
        String fieldName = spec.name;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        MethodSpec method = MethodSpec
                .methodBuilder("get" + fieldName)
                .returns(spec.type)
                .addStatement("return " + spec.name)
                .addModifiers(Modifier.PUBLIC)
                .build();

        return Optional.of(method);
    }

    public Optional<MethodSpec> setterSpec(FieldSpec spec) {
        String fieldName = spec.name;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        MethodSpec method = MethodSpec
                .methodBuilder("set" + fieldName)
                .returns(TypeName.VOID)
                .addParameter(spec.type, spec.name)
                .addStatement("this.$L  = $L", spec.name, spec.name)
                .addModifiers(Modifier.PUBLIC)
                .build();
        
        return Optional.of(method);
    }
}
