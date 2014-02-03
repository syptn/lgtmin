get "/", 						forward: "/index.groovy"
get "/g", 					forward: "/random.groovy"
get "/g/upload",  	forward: "/upload.groovy"
post "/g/save", 		forward: "/store.groovy"
get "/i/@hash", 		forward: "/load.groovy?hash=@hash"
get "/u/@hash", 		forward: "/upvote.groovy?hash=@hash"
get "/r/@hash", 		forward: "/report.groovy?hash=@hash"
get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"
get "/robots.txt/", redirect: "/robots.txt"
get "/cron/revalidate",  forward: "/cron/revalidate.groovy"
get "/auth/github", forward: "/auth/github.groovy"
get "/auth/me",     forward: "/auth/me.groovy"