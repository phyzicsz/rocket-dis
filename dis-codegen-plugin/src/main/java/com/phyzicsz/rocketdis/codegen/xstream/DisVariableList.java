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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.Optional;
import javax.lang.model.element.Modifier;

/**
 *
 * @author phyzicsz
 */
public class DisVariableList extends AbstractAttribute implements AbstractAttributeCodeGeneration{

    @XStreamAsAttribute
    private String countFieldName;
    
    private DisClassRef classRef;

    public String getCountFieldName() {
        return countFieldName;
    }

    public void setCountFieldName(String countFieldName) {
        this.countFieldName = countFieldName;
    }

    public DisClassRef getClassRef() {
        return classRef;
    }

    public void setClassRef(DisClassRef classRef) {
        this.classRef = classRef;
    }

    @Override
    public Optional<TypeName> typeName() {
        return classRef.typeName();
    }

    @Override
    public Optional<String> typeSize() {
        return classRef.typeSize();
    }

    @Override
    public Optional<FieldSpec> fieldSpec() {

        Optional<TypeName> typeNameOptional = typeName();
        if (typeNameOptional.isEmpty()) {
            return Optional.empty();
        }
        TypeName type = typeNameOptional.get();
        String name = getName().get();

        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        TypeName types = ParameterizedTypeName.get(arrayList, type);

        FieldSpec field = FieldSpec.builder(types, name)
                .addModifiers(Modifier.PROTECTED)
                .initializer("new $T<>()", arrayList)
                .build();

        return Optional.of(field);
    }
}
