import domain.ImageHolder
import domain.UserList
import io.reactivex.Observable
import services.LgtmService
import util.AppUtil

String userName = params.username
UserList myList = LgtmService.instance.getUserList(userName).blockingGet()

final int PAGESIZE = 32
final int page = params.page ? Integer.parseInt(params.page) : 1
final int start = (page - 1) * PAGESIZE
final int stop = Math.min(start + PAGESIZE - 1, myList.hashes.size() - 1)
log.info "Start: $start, Stop: $stop, ilS: ${myList.hashes.size()}"
if (start <= stop) {
    def renderedHashes = Observable.fromIterable(myList.hashes[start..stop])
    def images = renderedHashes.
            flatMapMaybe { LgtmService.instance.getImage(it) }.
            filter { !it.isDeleted }.
            map { new ImageHolder(it, true) }.
            blockingIterable().
            toList()

    request.setAttribute 'imageList', images
} else {
    request.setAttribute 'imageList', []
}
request.setAttribute 'username', userName
request.setAttribute 'appUtil', AppUtil.instance
if (stop + 1 < myList.hashes.size()) {
    request.setAttribute 'next', page + 1
}
if (start > 0) {
    request.setAttribute 'prev', page - 1
}

if (params.page != null) {
    forward '/WEB-INF/pages/browse.gtpl'
} else {
    forward '/WEB-INF/pages/browsePage.gtpl'
}
