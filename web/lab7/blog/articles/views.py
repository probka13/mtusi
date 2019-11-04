from .models import Article
from django.shortcuts import render, redirect
from django.http import Http404
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login

# Create your views here.

def archive(request):
    return render(request, 'archive.html', {"posts": Article.objects.all()})


def get_article(request, article_id):
    try:
        post = Article.objects.get(id=article_id)
        return render(request, 'article.html', {"post": post})
    except Article.DoesNotExist:
        raise Http404


def create_post(request):
    if not request.user.is_anonymous:
        if request.method == 'POST':
            form = {
                'text': request.POST['text'],
                'title': request.POST['title']
            }
            if form['text'] and form['title']:
                if form['title'] in [article.title for article in Article.objects.all()]:
                    form['errors'] = u'Название статьи совпадает'
                else:
                    article = Article.objects.create(text=form['text'],
                                                     title=form['title'],
                                                     author=request.user)
                    return redirect('get_article', article_id=article.id)
            else:
                form['errors'] = u'Не все поля заполнены'
            return render(request, 'create_post.html', {'form': form})
        else:
            return render(request, 'create_post.html', {})
    else:
        raise Http404


def sign_up_user(request):
    if request.user.is_anonymous:
        if request.method == 'POST':
            try:
                User.objects.get(username=request.POST["username"])
                form = {"response": "User already exists"}
            except User.DoesNotExist:
                User.objects.create_user(request.POST["username"],
                                         request.POST["email"],
                                         request.POST["password"])
                form = {"response": "Done! You are registered now"}
            return render(request, 'create_user.html', {'form': form})
        else:
            return render(request, 'create_user.html', {})
    else:
        form = {"response": "You are logged in"}
        return render(request, 'create_user.html', {'form': form})


def log_in_user(request):
    if not request.user.is_anonymous:
        if request.method == 'POST':
            if request.POST["username"] and request.POST["password"]:
                user = authenticate(username=request.POST["username"], password=request.POST["password"])
                if user is None:
                    try:
                        User.objects.get(username=request.POST["username"])
                        form = {"response": "Wrong password"}
                    except User.DoesNotExist:
                        form = {"response": "User doesn't exist"}
                else:
                    login(request, user)
                    form = {"response": "Congratulations! You're logged in"}
                return render(request, 'auth.html', {'form': form})
            else:
                form = {"response": "One of fields is empty"}
                return render(request, 'auth.html', {'form': form})
        else:
            return render(request, 'auth.html', {})
    else:
        form = {"response": "You are already logged in"}
        return render(request, 'auth.html', {'form': form})
