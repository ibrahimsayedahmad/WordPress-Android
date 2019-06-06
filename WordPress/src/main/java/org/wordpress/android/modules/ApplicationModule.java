package org.wordpress.android.modules;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import org.wordpress.android.ui.domains.DomainRegistrationDetailsFragment.CountryPickerDialogFragment;
import org.wordpress.android.ui.domains.DomainRegistrationDetailsFragment.StatePickerDialogFragment;
import org.wordpress.android.ui.news.LocalNewsService;
import org.wordpress.android.ui.news.NewsService;
import org.wordpress.android.ui.sitecreation.SiteCreationStep;
import org.wordpress.android.ui.sitecreation.SiteCreationStepsProvider;
import org.wordpress.android.ui.stats.refresh.StatsFragment;
import org.wordpress.android.ui.stats.refresh.StatsViewAllFragment;
import org.wordpress.android.ui.stats.refresh.lists.StatsListFragment;
import org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailFragment;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementFragment;
import org.wordpress.android.ui.stats.refresh.lists.widget.StatsWidgetSiteSelectionDialogFragment;
import org.wordpress.android.ui.stats.refresh.lists.widget.StatsViewsWidgetConfigureFragment;
import org.wordpress.android.ui.stats.refresh.lists.widget.ColorSelectionDialogFragment;
import org.wordpress.android.util.wizard.WizardManager;
import org.wordpress.android.viewmodel.helpers.ConnectionStatus;
import org.wordpress.android.viewmodel.helpers.ConnectionStatusLiveData;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ApplicationModule {
    // Expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);

    @Provides
    public static NewsService provideLocalNewsService(Context context) {
        return new LocalNewsService(context);
    }

    @ContributesAndroidInjector
    abstract StatsListFragment contributeStatListFragment();

    @ContributesAndroidInjector
    abstract StatsViewAllFragment contributeStatsViewAllFragment();

    @ContributesAndroidInjector
    abstract InsightsManagementFragment contributeInsightsManagementFragment();

    @ContributesAndroidInjector
    abstract StatsFragment contributeStatsFragment();

    @ContributesAndroidInjector
    abstract StatsDetailFragment contributeStatsDetailFragment();

    @ContributesAndroidInjector
    abstract CountryPickerDialogFragment contributeCountryPickerDialogFragment();

    @ContributesAndroidInjector
    abstract StatePickerDialogFragment contributeCStatePickerDialogFragment();

    @ContributesAndroidInjector
    abstract StatsViewsWidgetConfigureFragment contributeStatsViewsWidgetConfigureFragment();

    @ContributesAndroidInjector
    abstract StatsWidgetSiteSelectionDialogFragment contributeSiteSelectionDialogFragment();

    @ContributesAndroidInjector
    abstract ColorSelectionDialogFragment contributeViewModeSelectionDialogFragment();

    @Provides
    public static WizardManager<SiteCreationStep> provideWizardManager(SiteCreationStepsProvider stepsProvider) {
        return new WizardManager<>(stepsProvider.getSteps());
    }

    @Provides
    static LiveData<ConnectionStatus> provideConnectionStatusLiveData(Context context) {
        return new ConnectionStatusLiveData.Factory(context).create();
    }
}
