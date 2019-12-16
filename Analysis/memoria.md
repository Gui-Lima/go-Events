# Memória

Em geral, a memória usada não passou de 200mb. Há um pico ao iniciar o aplicativo, e a memória vai escalando com o tempo, oque é um problema. Foram detectados alguns leaks usando a ferramenta leakCanary, que foram resolvidos.

A atividade mais pesada não pareceu impactar na memória gasta, assim como nenhuma atividade em específico, oque é esperado, já que nenhuma atividade possui diversas ações ou jobs funcionando, no máximo sendo uma requisição ao backend.
