from django.shortcuts import render
from django.http import HttpResponse
from django import template

# Create your views here.

def home(request):
    return render(request, 'templates/index.html')

def handler(request):
    return render(request, 'templates/static_handler.html')
