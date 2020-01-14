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
package com.phyzicsz.rocketdis.codegen;

import com.phyzicsz.rocketdis.codegen.xstream.DisAttribute;
import com.phyzicsz.rocketdis.codegen.xstream.DisFixedList;
import com.phyzicsz.rocketdis.codegen.xstream.DisVariableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import java.util.Optional;
import javax.lang.model.element.Modifier;
import com.phyzicsz.rocketdis.codegen.xstream.AbstractAttributeCodeGeneration;

/**
 *
 * @author phyzicsz
 */
public class FieldGenerator {

//    public static Optional<FieldSpec> field(DisAttribute base) throws ClassNotFoundException {
//
//        Optional<AbstractAttributeCodeGeneration> attr = base.getAttributeType();
//        if (attr.isEmpty()) {
//            return Optional.empty();
//        }
//
//        AbstractAttributeCodeGeneration aat = attr.get();
//        if (aat instanceof DisFixedList) {
//            DisFixedList dis = (DisFixedList) aat;
//            Optional<TypeName> typeNameOptional = dis.getTypeName();
//            if (typeNameOptional.isEmpty()) {
//                return Optional.empty();
//            }
//            TypeName type = typeNameOptional.get();
//            String name = base.getName().get();
//            FieldSpec.Builder field = FieldSpec.builder(type, name)
//                    .addModifiers(Modifier.PROTECTED);
//            if (!type.isPrimitive()) {
//                field.initializer("new $T()", type);
//            } else {
//                field.initializer("new $T[$L]", type, dis.getLength());
//            }
//            return Optional.of(field.build());
//        } else if (aat instanceof DisVariableList) {
//            DisVariableList dis = (DisVariableList) aat;
//            Optional<TypeName> typeNameOptional = dis.getTypeName();
//            if (typeNameOptional.isEmpty()) {
//                return Optional.empty();
//            }
//            TypeName type = typeNameOptional.get();
//            String name = base.getName().get();
//
//            ClassName arrayList = ClassName.get("java.util", "ArrayList");
//            TypeName types = ParameterizedTypeName.get(arrayList, type);
//
//            FieldSpec field = FieldSpec.builder(types, name)
//                    .addModifiers(Modifier.PROTECTED)
//                    .initializer("new $T<>()", arrayList)
//                    .build();
//
//            return Optional.of(field);
//        } else {
//            Optional<TypeName> typeNameOptional = base.getAttributeType().get().getTypeName();
//            if (typeNameOptional.isEmpty()) {
//                return Optional.empty();
//            }
//            TypeName type = typeNameOptional.get();
//            String name = base.getName().get();
//
//            FieldSpec.Builder field = FieldSpec.builder(type, name)
//                    .addModifiers(Modifier.PROTECTED);
//            if (!type.isPrimitive()) {
//                field.initializer("new $T()", type);
//            }
//            return Optional.of(field.build());
//        }
//    }
}
