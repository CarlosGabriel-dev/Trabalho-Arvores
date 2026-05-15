public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = new No(null);
        System.out.println("Árvore criada com sucesso");
    }

//    public void inserir(Integer conteudo) {
//        No novoNo = new No(conteudo);
//        if(estaVazia()) {
//            this.raiz = novoNo;
//            System.out.println("Raiz criada com sucesso com valor: " + novoNo.getConteudo());
//        } else {
//            No aux = this.raiz;
//            while(true) {
//                if(aux.getConteudo() > novoNo.getConteudo()) {
////                    if(aux.getEsquerda() == null) {
////                        aux.setEsquerda(novoNo);
////                        System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
////                        return;
////                    } else {
////                        aux = aux.getEsquerda();
////                    }
////                } else if (aux.getConteudo() < novoNo.getConteudo()) {
////                    if(aux.getDireita() == null) {
////                        aux.setDireita(novoNo);
////                        System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
////                        return;
////                    } else {
////                        aux = aux.getDireita();
////                    }
////                } else {
////                    System.out.println("Não são permitidos nós repetidos na árvore binária. O " + novoNo.getConteudo() + " já existe na árvore.");
////                    return;
/// 
///                     //eu sou um viado 
////                }
//            }
//        }
//    }

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
        }
        else {
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
        buascarNo(conteudo, this.raiz);
    }

    private void buascarNo(Integer conteudo, No no) {
        if(no == null) {
            return;
        }

        buascarNo(conteudo, no.getDireita());
    }

    //REMOVE A RAIZ A DIREITA E A ESQUERDA 
    private void noRaiz(No no) {
        if(no.getConteudo() == this.raiz.getConteudo()) {
            System.out.println("O nó " + no.getConteudo() + " é a raiz da árvore.");
            if(no.getEsquerda() == null && no.getDireita() == null){
                no.setConteudo(null);
                System.out.println("A raiz da árvore foi removida com sucesso.");
            } else if (no.getEsquerda() != null && no.getDireita() != null){
                if(no.getEsquerda().getDireita() == null){
                    no.setConteudo(no.getEsquerda().getConteudo());
                    no.setEsquerda(null);
                } else {
                    No sucessor = no.getEsquerda();
                    No antecessor = sucessor;
                    while(sucessor.getDireita() != null) {
                        antecessor = sucessor;
                        sucessor = sucessor.getDireita();
                    }
                    no.setConteudo(sucessor.getConteudo());
                    sucessor.setConteudo(null);
                    antecessor.setDireita(sucessor.getEsquerda());
                }
            } else if (no.getEsquerda() != null && no.getDireita() == null) {
                no.setConteudo(no.getEsquerda().getConteudo());
                no.setEsquerda(null);
            } else if (no.getEsquerda() == null && no.getDireita() != null) {
                no.setConteudo(no.getDireita().getConteudo());
                no.setDireita(null);
            }
        }
    }

    //REMOVE NO FOLHA (NO QUE NÃO TENHA FILHOS)
    private void noFolha(No no) {
        if(no.getEsquerda() == null && no.getDireita() == null) {
            System.out.println("O nó " + no.getConteudo() + " é um nó folha.");
            no.setConteudo(null);
        }
    }

    //REMOVE UM NO QUE POSSUI APENAS UM FILHO
    private void noFilho(No no){
        if((no.getEsquerda() != null && no.getDireita() == null) || (no.getEsquerda() == null && no.getDireita() != null)) {
            System.out.println("O nó " + no.getConteudo() + " é um nó filho.");
            if(no.getEsquerda() != null) {
                no.setConteudo(no.getEsquerda().getConteudo());
                no.setEsquerda(null);
            } else {
                no.setConteudo(no.getDireita().getConteudo());
                no.setDireita(null);
            }
        }
    }

    //INDENTIFICA E REMOVE NO COM DOIS FILHOS
    private void noDoisFilhos(No no) {
        if(no.getEsquerda() != null && no.getDireita() != null) {
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
                sucessor.setConteudo(null);
                antecessor.setDireita(sucessor.getEsquerda());
            }
        }
    }
}
