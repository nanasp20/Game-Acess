
let slideIndex = 0;
const slides = document.querySelectorAll('.hero-slide');
const dots = document.querySelectorAll('.dot');

function showSlides() {
    if(slides.length === 0) return;
    
    slides.forEach(slide => slide.classList.remove('ativo'));
    dots.forEach(dot => dot.classList.remove('ativo'));
    
    slideIndex++;
    if (slideIndex > slides.length) {slideIndex = 1}    

    slides[slideIndex-1].classList.add('ativo');
    if(dots.length > 0) dots[slideIndex-1].classList.add('ativo');
    
    setTimeout(showSlides, 4000); 
}

function mudarSlide(n) {
    slideIndex = n;
    slides.forEach(slide => slide.classList.remove('ativo'));
    dots.forEach(dot => dot.classList.remove('ativo'));
    slides[slideIndex].classList.add('ativo');
    dots[slideIndex].classList.add('ativo');
}


const rows = document.querySelectorAll('.slider-wrapper');
rows.forEach(wrapper => {
    const container = wrapper.querySelector('.row-container');
    const leftBtn = wrapper.querySelector('.left-arrow');
    const rightBtn = wrapper.querySelector('.right-arrow');

    if (container && leftBtn && rightBtn) {
        rightBtn.addEventListener('click', () => container.scrollBy({ left: 300, behavior: 'smooth' }));
        leftBtn.addEventListener('click', () => container.scrollBy({ left: -300, behavior: 'smooth' }));
    }
});


function toggleMenu() {
    document.getElementById("sidebar").classList.toggle("aberto");
    document.getElementById("overlay").classList.toggle("ativo");
}

function toggleSearch() {
    const box = document.getElementById("searchBox");
    const input = document.getElementById("searchInput");
    if(box) box.classList.toggle("ativo");
    if(box && box.classList.contains("ativo") && input) input.focus();
}

function toggleNotif() {
    const notif = document.getElementById("notifDropdown");
    if(notif) notif.classList.toggle("ativo");
}

function toggleGenres() {
    const menu = document.getElementById("genreMenu");
    if(menu) menu.classList.toggle("ativo");
}


function confirmarAluguel(event) {
    
    event.preventDefault(); 
    
   
    alert("✅ Sucesso!\n\nJogo adicionado aos seus aluguéis.\nVocê será redirecionado para sua lista.");
    
   
    window.location.href = "alugueis.html"; 
}


function validarCadastro(event) {
    event.preventDefault(); 

    const campos = document.querySelectorAll('input');
   
    const senha = campos[2].value; 
    const confirma = campos[3].value;
    
    if (senha !== confirma) {
        alert("❌ As senhas não coincidem!");
        return false;
    }

    if (senha.length < 6) {
        alert("❌ A senha deve ter pelo menos 6 caracteres.");
        return false;
    }

    alert("✅ Cadastro realizado com sucesso! (Simulação)");
    window.location.href = "login.html"; 
}


document.addEventListener('DOMContentLoaded', () => {
    
    showSlides();

    
    const formCadastro = document.querySelector('form[action="login.html"]');
    if (formCadastro) {
        formCadastro.addEventListener('submit', validarCadastro);
    }

    
    const botoesAlugar = document.querySelectorAll('.btn-alugar, .btn-play');
    
    
    botoesAlugar.forEach(botao => {
        
        if(botao.innerText.includes("Alugar") || botao.innerText.includes("JOGAR") || botao.innerText.includes("ALUGAR")) {
            botao.addEventListener('click', confirmarAluguel);
        }
    });
});