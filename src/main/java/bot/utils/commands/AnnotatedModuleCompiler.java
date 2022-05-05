/*
 * Copyright 2016-2018 John Grosh (jagrosh) & Kaidan Gustave (TheMonitorLizard)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bot.utils.commands;


/**
 * A "compiler" for {@link Object Object}s that uses {@link java.lang.annotation.Annotation Annotation}s
 * as helpers for creating {@link Command Command}s.
 *
 * <p>Previous to version 1.6 all Commands required the Command abstract class to be extended in source.
 * The primary issue that came with this was that Commands were restricted to that method of creation, offering
 * no support for popular means such as annotated commands.
 *
 * <p>Since 1.6 the introduction of {@link CommandBuilder CommandBuilder}
 * has allowed the potential to create unique {@link Command Command}
 * objects after compilation.
 * <br>The primary duty of this class is to provide a "in runtime" converter for generics that are annotated with
 * the {@link JDACommand.Module JDACommand.Module}
 *
 * @author Kaidan Gustave
 * @since 1.7
 */

import java.util.List;

public interface AnnotatedModuleCompiler {
    List<Command> compile(Object o);
}
