public class ArvoreBinaria {
   No raiz;


   public ArvoreBinaria() {


       this.raiz = new No(null);
   }


   public void inserir(Integer conteudo) {


       No novoNo = new No(conteudo);


       if(estaVazia()) {
           this.raiz = novoNo;
           System.out.println("Raiz criada com sucesso com valor: " + novoNo.getConteudo());
       } else {
           inserirRecursivo(novoNo, this.raiz);
       }
   }


   public void inserirRecursivo(No novoNo, No aux) {


       if(aux.getConteudo() > novoNo.getConteudo()) {


           if(aux.getEsquerda() == null) {
               aux.setEsquerda(novoNo);
               System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
               return;
           } else {
               inserirRecursivo(novoNo, aux.getEsquerda());
           }
       } else if (aux.getConteudo() < novoNo.getConteudo()) {
           if(aux.getDireita() == null) {
               aux.setDireita(novoNo);
               System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
               return;
           } else {
               inserirRecursivo(novoNo, aux.getDireita());
           }
       } else {
           System.out.println("Não são permitidos nós repetidos na árvore binária. O " + novoNo.getConteudo() + " já existe na árvore.");
           return;
       }
   }


   private boolean estaVazia () {


   if(this.raiz.getConteudo() == null) {
       return true;
   } else {
       return false;
   }
}


   public void percurso(String percurso) {


       if(estaVazia()) {
           System.out.println("A árvore não existe.");
           return;
       }
      
       switch (percurso) {
           case("Pre"):
               System.out.println("Executando a árvore em pré ordem.");
               this.preOrdem(this.raiz);
               break;
           case("Em"):
               System.out.println("Executando a árvore em ordem.");
               this.emOrdem(this.raiz);
               break;
           case("Pos"):
               System.out.println("Executando a árvore em pós ordem.");
               this.posOrdem(this.raiz);
               break;
           default:
               System.out.println("Percurso inexistente!");
               break;
       }
   }


   private void posOrdem(No no) {
       if(no == null) {
           return;
       }
       posOrdem(no.getEsquerda());
       posOrdem(no.getDireita());
       System.out.println(no.getConteudo());
   }


   private void preOrdem(No no) {
       if(no == null) {
           return;
       }
       System.out.println(no.getConteudo());
       preOrdem(no.getEsquerda());
       preOrdem(no.getDireita());
   }


   private void emOrdem(No no) {
       if(no == null) {
           return;
       }
       emOrdem(no.getEsquerda());
       System.out.println(no.getConteudo());
       emOrdem(no.getDireita());
   }


   public void remover(Integer conteudo) {
       if(estaVazia()) {
           System.out.println("A árvore não existe.");
           return;
       }


       No noEncontrado = buscarNo(conteudo, this.raiz);


       if(noEncontrado != null) {
           identificarTipo(noEncontrado);
       } else {
           System.out.println("Este valor não está presente na árvore!");
       }
   }


   private No buscarNo(Integer conteudo, No no) {


       // chegou em um nó vazio
       if(no == null) {
           return null;
       }


       // encontrou o valor
       if(conteudo.equals(no.getConteudo())) {
           return no;
       }


       // procura na esquerda
       if(conteudo < no.getConteudo()) {
           return buscarNo(conteudo, no.getEsquerda());
       }


       // procura na direita
       return buscarNo(conteudo, no.getDireita());
   }


   private void identificarTipo(No no){


       if(no.getEsquerda() == null && no.getDireita() == null){
           removerNoFolha(no);
       }


       else if((no.getEsquerda() != null && no.getDireita() == null) || (no.getEsquerda() == null && no.getDireita() != null)){
           removerNoFilho(no);
       }


       else if(no.getEsquerda() != null && no.getDireita() != null){
           removerNoDoisFilhos(no);
       }
      
       else {
           System.out.println("Este valor não está presente na árvore!");
       }
   }


   public void buscarEIdentificar(Integer conteudo) {


       No noEncontrado = buscarNo(conteudo, raiz);


       if(noEncontrado != null) {
           identificarTipo(noEncontrado);
       }
      
       else {
           System.out.println("Este valor não está presente na árvore!");
       }
   }


   //REMOVE NO FOLHA (NO QUE NÃO TENHA FILHOS)
   private void removerNoFolha(No no) {


       System.out.println("O nó " + no.getConteudo() + " é um nó folha.");


       // REMOVE A RAIZ
       if(no == raiz) {
           raiz = new No(null);
           return;
       }


       No pai = raiz;
       No atual = raiz;


       // PROCURA O NÓ E GUARDA O PAI
       while(atual != null && atual != no) {


           pai = atual;


           if(no.getConteudo() < atual.getConteudo()) {
               atual = atual.getEsquerda();
           } else {
               atual = atual.getDireita();
           }
       }


       // REMOVE O NÓ
       if(pai.getEsquerda() == no) {
           pai.setEsquerda(null);
       } else {
           pai.setDireita(null);
       }
   }


   //REMOVE UM NO QUE POSSUI APENAS UM FILHO
   private void removerNoFilho(No no){


       System.out.println("O nó " + no.getConteudo() + " possui um filho.");
       No filho;


       // DESCOBRE QUAL É O FILHO
       if(no.getEsquerda() != null) {
           filho = no.getEsquerda();
       } else {
           filho = no.getDireita();
       }


       // REMOVE A RAIZ
       if(no == raiz) {
           raiz = filho;
           return;
       }


       No pai = raiz;
       No atual = raiz;


       // PROCURA O NÓ E GUARDA O PAI
       while(atual != null && atual != no) {


           pai = atual;


           if(no.getConteudo() < atual.getConteudo()) {
               atual = atual.getEsquerda();
           } else {
               atual = atual.getDireita();
           }
       }


       // LIGA O PAI DIRETAMENTE AO FILHO
       if(pai.getEsquerda() == no) {
           pai.setEsquerda(filho);
       } else {
           pai.setDireita(filho);
       }
   }


   //INDENTIFICA E REMOVE NO COM DOIS FILHOS
   private void removerNoDoisFilhos(No no) {
       System.out.println("O nó " + no.getConteudo() + " é um nó com dois filhos.");
       if(no.getEsquerda().getDireita() == null) {
           no.setConteudo(no.getEsquerda().getConteudo());
           no.setEsquerda(no.getEsquerda().getEsquerda());
       } else {
           No sucessor = no.getEsquerda();
           No antecessor = sucessor;
           while(sucessor.getDireita() != null) {
               antecessor = sucessor;
               sucessor = sucessor.getDireita();
           }
           no.setConteudo(sucessor.getConteudo());
           if(antecessor.getDireita() == sucessor) {
               antecessor.setDireita(sucessor.getEsquerda());
           }
           antecessor.setDireita(sucessor.getEsquerda());
       }
   }




   //VISUALIZAÇÃO DE ARVORE VIA TERMINAL
   public void imprimirArvore() {
       if (estaVazia()) {
           System.out.println("(árvore vazia)");
           return;
       }


       java.util.Queue<No> fila = new java.util.LinkedList<>();
       fila.add(this.raiz);
       int nivel = 0;


       while (!fila.isEmpty()) {
           int tamanho = fila.size();
           System.out.print("Nível " + nivel + (nivel == 0 ? " (Raiz): " : ": "));


           for (int i = 0; i < tamanho; i++) {
               No atual = fila.poll();
               System.out.print(atual.getConteudo());
               if (i < tamanho - 1) System.out.print(", ");


               if (atual.getEsquerda() != null) fila.add(atual.getEsquerda());
               if (atual.getDireita() != null) fila.add(atual.getDireita());
           }


           System.out.println();
           nivel++;
       }
   }
}
