package controlando.model.service;

import controlando.model.entity.Plano;
import controlando.model.entity.Site;
import controlando.model.entity.Usuario;
import controlando.model.entity.UsuariosDias;
import controlandomodel.LocalShell;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SquidService {

    public void startSquid() throws IOException {
        String comando = "/etc/init.d/squid3 start";
        final LocalShell shell = new LocalShell();
        shell.executeCommand(comando);
    }

    public void stopSquid() throws IOException {
        String comando = "/etc/init.d/squid3 stop";
        final LocalShell shell = new LocalShell();
        shell.executeCommand(comando);
    }

    public void reloadSquid() throws IOException {
        String comando = "/etc/init.d/squid3 reload";
        final LocalShell shell = new LocalShell();
        shell.executeCommand(comando);
    }

    public void restartSquid() throws IOException {
        String comando = "/etc/init.d/squid3 restart";
        final LocalShell shell = new LocalShell();
        shell.executeCommand(comando);
    }

    public void statusSquid() throws IOException {
        String comando = "/etc/init.d/squid3 status";
        final LocalShell shell = new LocalShell();
        shell.executeCommand(comando);
    }

    public void sarg() throws IOException {
        String comando = "sarg";
        final LocalShell shell = new LocalShell();
        shell.executeCommand(comando);
    }

    public void liberaIP(String ipCliente) throws FileNotFoundException, IOException {
        String localDoArquivo = "/etc/squid3/ips.txt";

        File file = new File(localDoArquivo);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();
        ArrayList<String> IPList = new ArrayList();

        while (linha != null) {
            if (linha.equals(ipCliente) == false) {
                IPList.add(linha);
            }
            linha = br.readLine();
        }

        br.close();
        fr.close();

        FileWriter apagaArquivo = new FileWriter(file, true);
        apagaArquivo.close();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < IPList.size(); i++) {
            bw.write(IPList.get(i));
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    public void bloqueiaIP(String ipCliente) throws FileNotFoundException, IOException {
        String localDoArquivo = "/etc/squid3/ips.txt";

        File file = new File(localDoArquivo);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();
        ArrayList<String> IPList = new ArrayList();

        while (linha != null) {
            IPList.add(linha);
            linha = br.readLine();
        }

        IPList.add(ipCliente);

        br.close();
        fr.close();

        FileWriter apagaArquivo = new FileWriter(file, true);
        apagaArquivo.close();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < IPList.size(); i++) {
            bw.write(IPList.get(i));
            bw.newLine();
        }

        bw.close();
        fw.close();

    }

    public void createACL(Plano entity) throws IOException {
        String comando = "acl " + entity.getNome() + " url_regex -i " + "\"/etc/squid3/" + entity.getNome() + ".txt\"";
        escreverConfigSquid(comando, "###Restrições###");

    }

    public void createACLIPUsuario(Usuario entity) throws IOException {
        String comando = "acl " + entity.getNome().replaceAll(" ", "") + " src " + entity.getIp();
        escreverConfigSquid(comando, "###Regras por Usuário###");
        createACLAcessoHTTP(entity);

    }
    public void removerACLIPUsuario(Usuario entity) throws IOException {
        String comando = "acl " + entity.getNome().replaceAll(" ", "") + " src " + entity.getIp();
        removerConfigSquid(comando);
        removerACLAcessoHTTP(entity);

    }

    public void createACLAcessoHTTP(Usuario entity) throws IOException {
        String comando = "http_access allow " + entity.getNome().replaceAll(" ", "") + " !" + entity.getPlano().getNome();
        escreverConfigSquid(comando, "###HTTP_ACCESS usuários###");
    }
     public void removerACLAcessoHTTP(Usuario entity) throws IOException {
        String comando = "http_access allow " + entity.getNome().replaceAll(" ", "") + " !" + entity.getPlano().getNome();
         removerConfigSquid(comando);
    }

    public void escreverConfigSquid(String acl, String add) throws FileNotFoundException, IOException {
        String localDoArquivo = "/etc/squid3/squid.conf";
        String novoArquivo = "/etc/squid3/squid2.conf";
        File file = new File(localDoArquivo);
        File file2 = new File(novoArquivo);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(file2);
        BufferedWriter bw = new BufferedWriter(fw);
        String linha = br.readLine();
        while (linha != null) {
            if (linha.equals(add)) {
                bw.write(linha + "\n");
                bw.write(acl);
                bw.newLine();

            } else {
                bw.write(linha);
                bw.newLine();
            }
            linha = br.readLine();
        }
        file.delete();
        file2.renameTo(file);
        bw.close();
        fw.close();
        br.close();
        fr.close();
    }
    public void removerConfigSquid(String acl) throws FileNotFoundException, IOException{
        String localDoArquivo = "/etc/squid3/squid.conf";
        String novoArquivo = "/etc/squid3/squid2.conf";
        File file = new File(localDoArquivo);
        File file2 = new File(novoArquivo);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(file2);
        BufferedWriter bw = new BufferedWriter(fw);
        String linha = br.readLine();
        while (linha != null) {
            if (linha.contains(acl)) {                              
                bw.newLine();

            } else {
                bw.write(linha);
                bw.newLine();
            }
            linha = br.readLine();
        }
        file.delete();
        file2.renameTo(file);
        bw.close();
        fw.close();
        br.close();
        fr.close();
        
    }

    public void gerarIp(String ip) throws IOException {
        String novoArquivo = "/etc/squid3/ips.txt";
        File file = new File(novoArquivo);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        String faixas[] = ip.split("/");
        String ips = faixas[0];
        String faixaIps[] = ips.split("[.]");
        Long ipFinal = Long.parseLong(faixaIps[faixaIps.length - 1]);

        for (int i = 3; i < 255; i++) {
            String ipf = "";
            for (int j = 0; j < faixaIps.length - 1; j++) {
                ipf += faixaIps[j] + ".";
            }
            ipf += i;
            bw.write(ipf);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    public List<Site> topSites() throws IOException {
        List<Site> siteList = new ArrayList<>();
        sarg();
        String caminho = "/var/www/squid-reports/index.html";
        String index = "";
        File file = new File(caminho);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linha = br.readLine();
        while (linha != null) {
            if (linha.contains("'2017")) {
                int count = linha.indexOf("2017");
                String data = linha.substring(31, 50);
                index = "/var/www/squid-reports/" + data + "/topsites.html";
                break;
            }
            linha = br.readLine();
        }
        file = new File(index);
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        linha = br.readLine();
        linha = br.readLine();
        while (linha != null) {
            if (linha.contains("http://sarg")) {
                linha = br.readLine();
            }

            if (linha.contains("http://")) {
                Site site = new Site();
                String quebraUrl[] = linha.split("<a");
                String quebra2Url[] = quebraUrl[1].split("</a>");
                String quebraQtd[] = quebra2Url[1].split("</td></tr>");
                int fim = quebraQtd[0].lastIndexOf(">");

                String quebra3Url[] = quebra2Url[0].split(">");
                site.setQtd(quebraQtd[0].substring(fim + 1, quebraQtd[0].length()));
                site.setNome(quebra3Url[1]);
                siteList.add(site);
            }
            linha = br.readLine();

        }
        return siteList;
    }

    public List<UsuariosDias> qtdUsuario5Dias() throws FileNotFoundException, IOException {
        List<UsuariosDias> usuariosDias = new ArrayList<>();
        sarg();
        String caminho = "/var/www/squid-reports/index.html";
        String index = "";
        File file = new File(caminho);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linha = br.readLine();
        int i = 0;
        while (linha != null && i < 5) {
            if (linha.contains("'2017")) {
                int count = linha.indexOf("2017");
                String data = linha.substring(count, 50);
                UsuariosDias ud = new UsuariosDias();
                ud.setDia(data);
                String qtd[] = linha.split("BRST");
                String td[] = qtd[1].split("<td");
                int comeco = td[1].indexOf(">");
                int fim = td[1].indexOf("</td>");
                ud.setQtd(td[1].substring(comeco + 1, fim));
                usuariosDias.add(ud);
                i++;
            }
            
            linha = br.readLine();
        }

        return usuariosDias;
    }
    
    public List<UsuariosDias> diaTotalMb() throws IOException{
        List<UsuariosDias> usuariosDias = new ArrayList<>();
        sarg();
        String caminho = "/var/www/squid-reports/index.html";
        String index = "";
        File file = new File(caminho);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String linha = br.readLine();
        int i = 0;
        while (linha != null && i < 5) {
            if (linha.contains("'2017")) {
                int count = linha.indexOf("2017");
                String data = linha.substring(count, 50);
                UsuariosDias ud = new UsuariosDias();
                ud.setDia(data);
                String qtd[] = linha.split("BRST");
                String td[] = qtd[1].split("<td");
                int comeco = td[2].indexOf(">");
                int fim = td[2].indexOf("</td>");
                ud.setQtd(td[2].substring(comeco + 1, fim-1));
                usuariosDias.add(ud);
                i++;
            }
            
            linha = br.readLine();
        }

        return usuariosDias;
    }
}
