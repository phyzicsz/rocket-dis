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

import com.phyzicsz.rocketdis.codegen.exceptions.CodeGenerationConfigurationException;
import com.phyzicsz.rocketdis.codegen.xstream.DisClass;
import com.phyzicsz.rocketdis.codegen.xstream.DisClasses;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisAttributeConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisClassConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisClassRefConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisFixedListConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisFlagConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisFlagsConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisListConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisPrimitiveConverter;
import com.phyzicsz.rocketdis.codegen.xstream.converters.DisVariableListConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class CodeGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);
    private final String javaPackage;
    private final File outputPath;
    private final Boolean generateTestSources;
    private final File testOutputPath;
    private static final String DIS_INPUT_FILE = "dis/dis7.xml";
   
    public CodeGenerator(final String javaPackage, 
            final File outputPath,
            final Boolean generateTestSources,
            final File testOutputPath){
        this.javaPackage = javaPackage;
        this.outputPath = outputPath;
        this.generateTestSources = generateTestSources;
        this.testOutputPath = testOutputPath;
               
    }
    
    public CodeGenerator generate() throws CodeGenerationConfigurationException, IOException, ClassNotFoundException{

        Class<?>[] allowedClasses = new Class[] { DisClasses.class};
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(DisClasses.class);
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(allowedClasses);
        xstream.registerConverter(new DisAttributeConverter());
        xstream.registerConverter(new DisClassConverter());
        xstream.registerConverter(new DisFlagConverter());
        xstream.registerConverter(new DisFlagsConverter());
        xstream.registerConverter(new DisPrimitiveConverter());
        xstream.registerConverter(new DisClassRefConverter());
        xstream.registerConverter(new DisListConverter());
        xstream.registerConverter(new DisVariableListConverter());
        xstream.registerConverter(new DisFixedListConverter());
        

        URL url = getClass().getClassLoader().getResource(DIS_INPUT_FILE);
        
//        LOGGER.info("Cleaning output directory: {}", outputPath);
//        FileUtils.deleteDirectory(outputPath);
//        LOGGER.info("Cleaning output directory: {}", testOutputPath);
//        FileUtils.deleteDirectory(testOutputPath);
                
        
        LOGGER.info("parsing DIS XML File: {}", DIS_INPUT_FILE);
        DisClasses classes = (DisClasses)xstream.fromXML(url);
        LOGGER.info("parsed {} classes: ", classes.getClasses().size());
        
        LOGGER.info("generating base interface {}: ", "AbstractDisObject");
        new DisInterfaceGenerator()
                .generate(javaPackage)
                .writeClassFile(outputPath);     
        
        for (DisClass disClass : classes.getClasses()) {
            
            if(disClass.getName().get().contains("IFFFundamentalParameterData"))
            {
                int i = 0;
            }
            try {
                LOGGER.info("generating class {}: ", disClass.getName());
                new DisClassGenerator()
                        .generate(javaPackage, disClass)
                        .writeClassFile(outputPath);
                
                if(generateTestSources){
                    LOGGER.info("generating test class {}: ", disClass.getName());
                    new DisTestClassGenerator()
                            .generate(javaPackage, disClass)
                            .writeClassFile(testOutputPath);
                }
            } catch (IOException ex) {
                LOGGER.error("Error Writing File: ", ex);
            }
        }
         
        return this; 
    }
}
