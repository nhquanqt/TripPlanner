# Generated by Django 3.1.4 on 2021-01-15 13:15

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Tripplaner', '0003_share'),
    ]

    operations = [
        migrations.AddField(
            model_name='expense',
            name='date',
            field=models.CharField(max_length=255, null=True),
        ),
        migrations.AddField(
            model_name='expense',
            name='place',
            field=models.CharField(max_length=255, null=True),
        ),
    ]