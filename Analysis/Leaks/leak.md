# Leaks

Foram detectados alguns Memory Leaks usando a ferramenta LeakCanary, no fragment de Profile, que é onde podem ser iniciadas várias activities. Porém eu não consegui detectar exatamente oque estava fazendo os leaks. Alterei algumas linhas de código e tive mais atenção para a destruição de activities mas tudo parecia normal. Foi um Leak recorrente que não foi resolvido.
