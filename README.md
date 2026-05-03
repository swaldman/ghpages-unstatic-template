# ghpages-unstatic-template

_That's a catchy name, right?_

### Introduction

[_unstatic_](https://github.com/swaldman/unstatic) is a library for building flexible, customizable static-site generators.

The purpose of this repository is to make it simple to generate your own basic _unstatic_-based static-site generator,
which you can then customize as you will. The default site is for a basic blog.

By default (unless you disable it), your site will automatic regenerate itself and publish itself to github pages
whenever you push it to the `main` branch of a github repository.

It is intended to be simple enough for a modestly technical person to conveniently use. You do need to be comfortable
editing plaintext markdown files with weird headers.

(I am working on infrastructure to generate static sites for less technical authors, with a wordpress style interface.
But this isn't that.)

### Getting Started

Here's a quick tutorial.

Note that I am following the tutorial as I write it. I describe my choices in _italicized_ sections.
You should make your own choices, rather than reproduce mine!

#### I. Create and configure a github repository

1. Log into your github account and create the repository that will host your website [in the usual way](https://github.com/new).
   github's defaults are fine. Just provide the name.
   _For the purposes of this tutorial, I'll call my repository `my-unstatic-site`._

2. Under your new blank repository, click into "Settings" (on the horizontal menu below your repo name).
   Then Click into "Pages" on the sidebar within Settings. Under **Build and Deployment**, modify the dropdown
   from "Deploy from a branch" to "GitHub Actions".

3. If you mean to make your site available under your own custom domain, scroll to the bottom of the page and provide your custom
   domain. You will need to set up appropriate DNS; see
   [github's documentation](https://docs.github.com/en/pages/configuring-a-custom-domain-for-your-github-pages-site/managing-a-custom-domain-for-your-github-pages-site).

_For this tutorial, I've set up a custom domain, `https://test-unstatic.mchange.com`._

#### II. Clone and configure this repository

1. Clone this repository. That's just

   ```plaintext
   $ git clone git@github.com:swaldman/ghpages-unstatic-template.git
   ```

2. Edit `Params` in the file called `mill.build`.

   Most users will edit only the uncommented values for
   - `siteServerAbsUrl` &mdash; this will usually look something like `https://www.mydomain.com/` or else
     `https://<user>.github.io/`, where repo name is the github repo
     you configured above.
   - `title` &mdash; this will become the user-visible title of the generated site

   _For this tutorial, I've set `siteServerAbsUrl` to `https://test-unstatic.mchange.com/` and
   title to `Getting Started Example`._

   Here's what `Params` looks like. You can uncomment and override other values if you wish.
   For available time zones, run `./mill showTimeZones` in this repository.

   ```scala
   // NOTE: if you will not attach this to a custom domain,
   //       then siteServerAbsUrl should be something like
   //       https://<user>.github.io/ and sitePath should
   //       be "/<repo-name>"
   val Params = ghpages.unstatic.template.Params(
     siteServerAbsUrl = "https://www.example.com/", // must end with a slash!
     title            = "Example Blog",
     // maxFrontPageEntries   = 5,
     // timeZone              = "America/New_York",
     // sitePath              = "/",
     // sitePackage           = "my.unstatic.website",
     // millVersion           = "1.0.6",
     // millJvmVersion        = "21",
     // untemplateMillVersion = "0.3.0",
     // unstaticVersion       = "0.4.0",
     // scalaVersion          = "3.3.7",
     // entriesSubpackage     = "entries",
     // entriesDir            = "entries",
   )
   ```

#### III. Generate your static-site generator

1. Decide where you'd like your new static-site repository to be generated.
   You'll need to provide an absolute URL for a directory that will be created.
   Almost always, the name of that directory should be the same as the name
   as the repository you configured on github.

   _For the purposes of this tutorial, I'll choose the directory `/Users/swaldman/tmp/my-unstatic-site`_

2. Inside the customized `ghpages-unstatic-template` repository, run the following command:

   ```plaintext
   $ ./mill create <absolute-path-to-desired-directory>
   ```

   _So, for me, I'll run `./mill create /Users/swaldman/tmp/my-unstatic-site`_

   Very quickly, you will find a directory exists where you specified it should!

   You are now done with your clone of this repository. You can delete it if you want.

#### IV. Generate the placeholder site

1. Change into your new static-site generator home directory.

   _For me, that's `cd /Users/swaldman/tmp/my-unstatic-site`_

2. Run the following command

   ```plaintext
   $ ./site gen
   ```

You should see that a `public` directory has been created, containing
an `index.html` file. Open that file in a web browser, and check out
your generated site!

#### V. Push and publish your site

Your site will automatically (re)publish itself whenever you push it to
the branch `main` on github.

1. Set up the repository you configured on github to be the remote for
   your new static-site generator. This is just

   ```plaintext
   $ git remote add origin git@github.com:<user-name>/<repo-name>.git
   $ git branch -M main
   $ git push -u origin main
   ```
2. In a few minutes, your placeholder site should appear, either at `https://<user-name>.github.io/<repo-name>/`,
   or at the custom domain that you configured!

> [!NOTE]
> If you've configured a custom domain, you may see an ugly warning about an invalid certificate.
> It takes github a while to generate SSL certificates for your custom domain.
> Try viewing your site with an `http` rather than `https` URL if that happens, or just wait a while.

#### VI. Create your real first post

In the `entries` directory, you'll see files like `entry-hello.md.untemplate` and `entry-hello.md.untemplate-off`.
Those files are identical. `entry-hello.md.untemplate-off` is only present as an example file that you can leave
around, showing the format of an unstatic post.

Let's create a "real" first post!

1. Rename `entry-hello.md.untemplate` to something like `entry-first-post.md.untemplate`

   You can pick any file name that you choose, subject to the following constraints:
   1. Your entry filenames should begin with `entry-`
   2. Your entry filenames should end with one of `.md.untemplate` or `.html.untemplate`

   You can organize your posts beneath your `entries` directory however you like. You can place
   them in subdirectories by year or month or whatever. The URLs of your posts have nothing
   to do with where you place them in the filesystem.

2. Edit the file that you've renamed. You'll see that it starts with a header like

   ```plaintext
   > val uid = "hello" // a unique ID you can use in future posts to refer to this one
   >
   > val UntemplateAttributes = immutable.Map[String,Any] (
   >   "Title"         -> "Hello!",
   >   "Author"        -> "Scrooge McDuck",
   >   "PubDate"       -> "2026-05-02T16:07:50.480574-04:00",
   >   "Anchor"        -> uid
   > )

   given PageBase = PageBase.fromPage(input.renderLocation)

   (input : MainBlog.EntryInput)[]~()>      ### modify Title/Author/Pubdate above, add markdown or html below!
   ```

   Change the UID to some appropriate opaque string (e.g. `first-post`), change the title and author and, if you wish
   the publication time. Otherwise, leave this header as-is for now!

   Below the header the file just looks like

   ```markdown
   This is an automatically generated placeholder entry in your new blog.
   You'll probably want to delete it.

   But do copy it first, as a template for your first new, real post!
   ```

   This is just ordinary markdown text. Go ahead and write the body of your post!

   (If you changed the name of this file to something ending with `.html.untemplate`, then
   you would place HTML &mdash; an embeddable fragment, not a full page &mdash; in the body
   of the entry.)

#### VII. Preview and continuously edit your post.

Once you have a draft of your first post, inside your new static-site generator, run the following
command:

```plaintext
$ ./devcycle
```

Now point a browser to `http://localhost:8999`. You'll see a preview of your site!

Edit your post, and hit refresh in your browser, until you are happy with your work.

> [!NOTE]
> If you set a non-root `sitePath`, then you'll need to look at `http://localhost:8999/sitePath/`

#### VIII. Republish your website

Once you are happy with your work, Hit `<ctrl>-c` to end the `./devcycle` script.

To republish your site, all you need to do is commit it to `git` and then `push`
to github. The simplest approach is just...

```plaintext
$ git add .
$ git commit -m "Write first real post."
$ git push
```

Though of course it's best to use `git status`, `git diff`, and more selective `git add` if you
are comfortable using those tools.

In any case, every push to main republishes. (It may take a few minutes though.)

#### IX. More posts, with media and safe references

You can add as many posts as you want. What determines their ordering is the value
of the "PubDate" attribute.

But suppose you want to add an image? We said the location of the post "untemplate"
files under entries doesn't matter. How do we refer to images or other media?

Every post has its own, dedicated media directory! To explore this, let's create
a draft of a second post:

```plaintext
> val uid = "second-post-with-media" // a unique ID you can use in future posts to refer to this one
>
> val UntemplateAttributes = immutable.Map[String,Any] (
>   "Title"         -> "Second post, with media!",
>   "Author"        -> "Steve",
>   "PubDate"       -> "2026-05-02T20:00:00-03:00",
>   "Anchor"        -> uid
> )

given PageBase = PageBase.fromPage(input.renderLocation)

(input : MainBlog.EntryInput)[]~()>      ### modify Title/Author/Pubdate above, add markdown or html below!

Here is the stub of our second post. I'd like to add media below, but I don't know how!
```

We'll put this under `entries` as `entry-second-post.md.untemplate`.

Now we can use `./devcycle` (from the top level of our site repository) to preview our site
at `http://localhost:8999/` (or `http://localhost:8999/<sitePath>` if you set a non-root `sitePath`).
You should see your incomplete second post!

Great. `<ctrl>-c` out of `./devcycle`, and try the following command:

```plaintext
$ ./site list
```

You should see information about surprisingly many endpoints already generated by your little site!

We are interested in the post generated to a URL containing "econd". So let's try the following:

```plaintext
$ ./site list -f econd
```

We should see something like

```plaintext
[79/79] run
[79] Location: /2026/05/02/second-post-with-media/index.html
[79]   String endpoint of type 'text/html; charset=UTF-8'.
[79]   Static generation and HTTP service.
[79]     media-dir: /2026/05/02/second-post-with-media/
[79]     media-dir (absolute): 
[79]       /Users/swaldman/tmp/my-unstatic-site/static/2026/05/02/second-post-with-media
[79]     uid: entry_second_post_md
[79] Location: /2026/05/02/second-post-with-media/index.rss
[79]   Binary endpoint of type 'application/rss+xml'.
[79]   Static generation and HTTP service.
[79]     uid: entry_second_post_md-single-item-rss
```

Why didn't we use `Second` for our `-f` filter?

Because by default, URLs are generated from titles pushed into lowercase.
We could have used `second`, but to avoid ambiguity, `econd` is easy.

> [!NOTE]
> You can modify your site to set-up URLs according to any scheme you like.
> But that's a more advanced topic.

We can see there are already two endpoints containing "econd", our post,
and a [single-item RSS](https://tech.interfluidity.com/2024/11/12/supporting-single-item-rss/index.html) 
document that is generated for that post.

But the actual blog post has a media directory: `/Users/swaldman/tmp/my-unstatic-site/static/2026/05/02/second-post-with-media`

We make sure that directory exists, and drop an image file into it:

```plaintext
$ mkdir -p /Users/swaldman/tmp/my-unstatic-site/static/2026/05/02/second-post-with-media
$ mv ~/Downloads/IMG_5513.jpeg /Users/swaldman/tmp/my-unstatic-site/static/2026/05/02/second-post-with-media/
```

Now we can refer to the images location with a special synatax: `##./`

So we edit our stub post as follows:

```plaintext
> val uid = "second-post-with-media" // a unique ID you can use in future posts to refer to this one
>
> val UntemplateAttributes = immutable.Map[String,Any] (
>   "Title"         -> "Second post, with media!",
>   "Author"        -> "Steve",
>   "PubDate"       -> "2026-05-02T20:00:00-03:00",
>   "Anchor"        -> uid
> )

given PageBase = PageBase.fromPage(input.renderLocation)

(input : MainBlog.EntryInput)[]~()>      ### modify Title/Author/Pubdate above, add markdown or html below!

Montevideo in May is lovely!

![A tree-lined Montevideo street in May 2026](##./IMG_5513.jpeg)
```

Now we can re-run `./devcycle`. Lo and behold, there is our image!

We can of course put subdirectories beneath our media directory, and
organize things any way that we please.

Let's also add a link back to our first post. Of course, we could just copy the permalink
URL from our published first post, but it's much better if all internal links are relative,
so that the site is portable and readable offline.

Recall how we are giving each post a UID? Here's how we use it. Let's add to the end of
our second post the following:

```plaintext

This is our second post. Check out our [first](##first-post).
```

That yields HTML like this

```html
<p>This is our second post. Check out our <a href="../first-post/index.html">first</a>.</p>
```

with a nice, relatived URL. If we had referred to an unknown UID, we would have seen an error
when viewing the post in `./devcycle`

We have special syntax for...
- Relative links to files in the current post's media directory: `##./<path-from-media-dir`
- Relative links to posts with UIDs: `##<uid>`
- Relative links to anything on the site, relative to site root (server root + any `sitePath`): `##/<path-from-site-root>`

Let's not forget to publish our second post! It's just

```plaintext
$ git add .
$ git commit -m "Publish second post."
$ git push
```

#### X. Congratulations!

There is lots more to talk about and understand. `unstatic`-based sites are protean and extensible. You can make them
do almost anything.

The easiest way to customize your site, of course, is to just modify `css/style.css`.

But you can also modify the structure of your site, which is defined by [untemplates](https://github.com/swaldman/untemplate) in the `untemplate` directory.

Please check out the [untemplate docs](https://github.com/swaldman/untemplate-doc#readme)!

You can see the results of my run of this tutorial [here](https://test-unstatic.mchange.com/).


