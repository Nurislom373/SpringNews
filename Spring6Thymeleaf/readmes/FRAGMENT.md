# Thymeleaf Fragments

Thymeleaf has the concept of fragments, which are basically re-usable snippets of HTML. They are
very similar to methods. Just as you use methods to better structure your Java code, you use
fragments to better structure your HTML pages.
You define a fragment using th:fragment.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div th:fragment="separator"> ①
    <div class="border-dashed border-2 border-red-300 mx-4">
    </div>
</div>
</body>
</html>
```

You can define a fragment on any HTML tag, it does not need to be a <div>.

## Using fragments

We can now use this fragment in any Thymeleaf template. Suppose the above fragment is in a file
called _fragments.html_. We can reference the specific _separator_ fragment using this _~{filename
:: fragmentname}_ syntax:

```html
<div>
  <div>There is some content here.</div>
  <div th:insert="~{fragments :: separator}"></div> ①
  <div>There is some more content here.</div>
</div>
```

## Fragments with parameters

Just like methods in Java code can have parameters, so can fragments in Thymeleaf have parameters.
As an example, we can imagine a menu item that is a fragment like this:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<a th:fragment="menu-item(title, link)" ①
  th:text="${title}" ②
  th:href="${link}" ③
  class="flex items-center px-2 py-2 text-base leading-6 font-medium
text-gray-900">
</a>
</body>
</html>
```

1. Declare the menu-item fragment with 2 parameters: title and link
2. Use the value of the title parameter as the link text
3. Use the value of the link parameter as the hyperlink reference

```html
<a th:replace="fragments :: menu-item('Users', '/users')"></a>
<a th:replace="fragments :: menu-item('Groups', '/groups')"></a>
```

Notice how there is really no need to create a custom CSS class for the classes that are the same over all menu items as
we now have a fragment that has all the knowledge about how a menu item should look.

The resulting HTML will be this:

```html
<a href="/users" class="flex items-center px-2 py-2 text-base leading-6
font-medium text-gray-900">Users</a>
<a href="/groups" class="flex items-center px-2 py-2 text-base leading-6
font-medium text-gray-900">Groups</a>
```

If you want to make the parameters explicit on the calling side, that is possible like this:

```html
<a th:replace="fragments :: menu-item(title='Users',
link='/users')"></a>
<a th:replace="fragments :: menu-item(title='Groups',
link='/groups')"></a>
```