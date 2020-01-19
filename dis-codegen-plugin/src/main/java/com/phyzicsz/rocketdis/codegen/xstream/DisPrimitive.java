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

import com.phyzicsz.rocketdis.codegen.TypeMapper;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisPrimitiveConverter;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import java.util.Optional;
import javax.lang.model.element.Modifier;

/**
 *
 * @author phyzicsz
 */
@XStreamConverter(DisPrimitiveConverter.class)
public class DisPrimitive extends AbstractAttribute implements AbstractAttributeCodeGeneration{
    
    @XStreamAsAttribute
    private Optional<String> type = Optional.empty();
    
    private Optional<DisFlags> flags = Optional.empty();

    public Optional<String> getType() {
        return type;
    }

    public void setType(String type) {
        this.type = Optional.ofNullable(type);
    }

    public Optional<DisFlags> getFlags() {
        return flags;
    }

    public void setFlags(DisFlags flags) {
        this.flags = Optional.ofNullable(flags);
    }

    @Override
    public Optional<TypeName> typeName() {
        if(type.isPresent()){
            return  Optional.of(TypeMapper.typeMapper(type.get()));
        }else{
            return Optional.empty();
        }
        
    }

    @Override
    public Optional<String> typeSize() {
        if(type.isPresent()){
            return Optional.of(TypeMapper.getSize(type.get()));
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
        String name = this.getName().get();

        FieldSpec.Builder field = FieldSpec.builder(type, name)
                .addModifiers(Modifier.PROTECTED);
        if (!type.isPrimitive()) {
            field.initializer("new $T()", type);
        }
        return Optional.of(field.build());
    }

}
