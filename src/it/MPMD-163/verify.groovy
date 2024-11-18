/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import groovy.xml.XmlSlurper

File buildLog = new File( basedir, 'build.log' )
assert buildLog.exists()

File pmdXml = new File( basedir, "target/pmd.xml" )
assert pmdXml.exists()

def pmd = new XmlSlurper().parse( pmdXml )
def version = pmd.@version

assert buildLog.getText().contains('[WARNING] PMD ' + version + ' has issued 1 warning. For more details see:')
assert buildLog.getText().contains("[WARNING] PMD Warning: Foo:23 Rule:UnusedFormalParameter Priority:3 Avoid unused constructor parameters such as 'foo'..")
