import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
// import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpeaker } from 'app/shared/model/speaker.model';
import { AccountService } from 'app/core';
import { SpeakerService } from './prize.service';

@Component({
  selector: 'jhi-speaker',
  templateUrl: './prize.component.html'
})
export class PrizeComponent implements OnInit, OnDestroy {
  speakers: ISpeaker[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected speakerService: SpeakerService,
    // protected jhiAlertService: JhiAlertService,
    // protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.speakerService
      .query()
      .pipe(
        filter((res: HttpResponse<ISpeaker[]>) => res.ok),
        map((res: HttpResponse<ISpeaker[]>) => res.body)
      )
      .subscribe(
        (res: ISpeaker[]) => {
          this.speakers = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    // this.loadAll();
    // this.accountService.identity().then(account => {
    //   this.currentAccount = account;
    // });
    // this.registerChangeInSpeakers();
  }

  ngOnDestroy() {
    // this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISpeaker) {
    return item.id;
  }

  registerChangeInSpeakers() {
    // this.eventSubscriber = this.eventManager.subscribe('speakerListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    // this.jhiAlertService.error(errorMessage, null, null);
  }
}
